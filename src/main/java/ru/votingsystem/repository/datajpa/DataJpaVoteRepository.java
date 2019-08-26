package ru.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaVoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    public Integer deleteVoteByUserIdAndDateVoting(Integer userId, LocalDate date) {
        return repository.deleteVoteByUserIdAndDateVoting(userId, date);
    }

    public Integer countAllByRestaurantIdAndDateVoting(Integer restaurantId, LocalDate date) {
        return repository.countAllByRestaurantIdAndDateVoting(restaurantId, date);
    }

    public Integer countAllByRestaurantIdAndDateVotingBetween(Integer restaurantId, LocalDate startDate, LocalDate endDate) {
        return repository.countAllByRestaurantIdAndDateVotingBetween(restaurantId, startDate, endDate);
    }

    public Optional<Vote> findByUserIdAndDateVoting(Integer userId, LocalDate localDate) {
        return repository.findByUserIdAndDateVoting(userId, localDate);
    }

}
