package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.votingsystem.model.Vote;
import ru.votingsystem.repository.datajpa.CrudVoteRepository;
import ru.votingsystem.repository.datajpa.DataJpaRestaurantRepository;
import ru.votingsystem.repository.datajpa.DataJpaUserRepository;
import ru.votingsystem.util.exception.VoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static ru.votingsystem.util.DateTimeUtil.DEFAULT_EXPIRED_TIME;
import static ru.votingsystem.util.DateTimeUtil.currentDate;
import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final CrudVoteRepository voteRepository;
    private final DataJpaUserRepository userRepository;
    private final DataJpaRestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(CrudVoteRepository voteRepository,
                       DataJpaRestaurantRepository restaurantRepository,
                       DataJpaUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public Vote vote(Integer userId, Integer restaurantId, LocalTime time) {
        Optional<Vote> votes = voteRepository.findByUserIdAndDateVoting(userId, currentDate());
        return voteRepository.save(votes.map(v -> {
            if (time.isAfter(DEFAULT_EXPIRED_TIME)) {
                throw new VoteException("today the voting time has expired");
            }
            v.setRestaurant(restaurantRepository.get(restaurantId));
            return v;
        }).orElse(new Vote(LocalDate.now(), userRepository.get(userId), restaurantRepository.get(restaurantId))));
    }

    public void deleteVoteByUserIdAndDateVoting(Integer userId, LocalDate date) {
        if (LocalTime.now().isAfter(DEFAULT_EXPIRED_TIME)){
            throw new VoteException("today the vote cannot delete");
        }
        checkNotFoundWithId(voteRepository.deleteVoteByUserIdAndDateVoting(userId, date), userId);
    }

    public Integer countAllByRestaurantIdAndDateVoting(Integer restaurantId, LocalDate date) {
        return voteRepository.countAllByRestaurantIdAndDateVoting(restaurantId, date);
    }

    public Integer countAllByRestaurantIdAndDateVotingBetween(Integer restaurantId, LocalDate startDate, LocalDate endDate) {
        return voteRepository.countAllByRestaurantIdAndDateVotingBetween(restaurantId, startDate, endDate);
    }
}
