package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.votingsystem.model.Vote;
import ru.votingsystem.repository.CrudRestaurantRepository;
import ru.votingsystem.repository.CrudUserRepository;
import ru.votingsystem.repository.CrudVoteRepository;
import ru.votingsystem.util.exception.NotFoundException;
import ru.votingsystem.util.exception.VoteException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static ru.votingsystem.util.DateTimeUtil.DEFAULT_EXPIRED_TIME;
import static ru.votingsystem.util.DateTimeUtil.currentDate;

@Service
public class VoteService {
    private final CrudVoteRepository voteRepository;
    private final CrudUserRepository userRepository;
    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public VoteService(CrudVoteRepository voteRepository,
                       CrudRestaurantRepository restaurantRepository,
                       CrudUserRepository userRepository) {
        this.voteRepository = voteRepository;
        this.restaurantRepository = restaurantRepository;
        this.userRepository = userRepository;
    }

    public List<Vote> getAllByDateVoting(LocalDate dateVoting) {
        return voteRepository.getAllByDateVoting(dateVoting);
    }

    public Vote vote(Integer userId, Integer restaurantId, LocalTime time) {
        Optional<Vote> votes = voteRepository.findByUserIdAndDateVoting(userId, currentDate());
        if (restaurantRepository.getAllOnDate(currentDate()).stream()
                .noneMatch(restaurant -> Objects.equals(restaurant.getId(), restaurantId))) {
            throw new NotFoundException("Restaurant don't have meal today");
        }
        return voteRepository.save(votes.map(v -> {
            if (time.isAfter(DEFAULT_EXPIRED_TIME)) {
                throw new VoteException("today the voting time has expired");
            }
            v.setRestaurant(restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Not found restaurant with id " + restaurantId)));
            return v;
        }).orElse(new Vote(LocalDate.now(), userRepository.findById(userId).orElse(null),
                restaurantRepository.findById(restaurantId).orElseThrow(() -> new NotFoundException("Not found restaurant with id " + restaurantId)))));
    }

    public Integer countAllByRestaurantIdAndDateVoting(Integer restaurantId, LocalDate date) {
        return voteRepository.countAllByRestaurantIdAndDateVoting(restaurantId, date);
    }

    public Integer countAllByRestaurantIdAndDateVotingBetween(Integer restaurantId, LocalDate startDate, LocalDate endDate) {
        return voteRepository.countAllByRestaurantIdAndDateVotingBetween(restaurantId, startDate, endDate);
    }
}
