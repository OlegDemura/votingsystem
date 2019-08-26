package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.votingsystem.model.Vote;
import ru.votingsystem.repository.datajpa.DataJpaRestaurantRepository;
import ru.votingsystem.repository.datajpa.DataJpaUserRepository;
import ru.votingsystem.repository.datajpa.DataJpaVoteRepository;
import ru.votingsystem.util.exception.VoteRepeatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static ru.votingsystem.util.DateTimeUtil.DEFAULT_EXPIRED_TIME;

@Service
public class VoteService {
    private final DataJpaVoteRepository voteRepository;
    private final DataJpaUserRepository userRepository;
    private final DataJpaRestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(DataJpaVoteRepository voteRepository,
                       DataJpaRestaurantRepository restaurantRepository,
                       DataJpaUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote vote(Integer userId, Integer restaurantId){
        Optional<Vote> votes = voteRepository.findByUserIdAndDateVoting(userId, LocalDate.now());
        return voteRepository.save(votes.map(v->{
            if (LocalTime.now().isBefore(DEFAULT_EXPIRED_TIME)){
                throw new VoteRepeatException("today the voting time has expired");
            }
            v.setRestaurant(restaurantRepository.get(restaurantId));
            return v;
        }).orElse(new Vote(LocalDate.now(), userRepository.get(userId), restaurantRepository.get(restaurantId))));
    }
}
