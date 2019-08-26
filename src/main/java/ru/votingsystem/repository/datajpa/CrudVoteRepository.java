package ru.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @Transactional
    Integer deleteVoteByUserIdAndDateVoting(Integer userId, LocalDate date);

    Integer countAllByRestaurantIdAndDateVoting(Integer restaurantId, LocalDate date);

    Integer countAllByRestaurantIdAndDateVotingBetween(Integer restaurantId, LocalDate startDate, LocalDate endDate);

    Optional<Vote> findByUserIdAndDateVoting(Integer userId, LocalDate localDate);
}
