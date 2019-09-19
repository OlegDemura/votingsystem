package ru.votingsystem.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface CrudVoteRepository extends JpaRepository<Vote, Integer> {

    @EntityGraph(attributePaths = {"restaurant"}, type = EntityGraph.EntityGraphType.LOAD)
    List<Vote> getAllByDateVoting(LocalDate dateVoting);

    Integer countAllByRestaurantIdAndDateVoting(Integer restaurantId, LocalDate date);

    Integer countAllByRestaurantIdAndDateVotingBetween(Integer restaurantId, LocalDate startDate, LocalDate endDate);

    Optional<Vote> findByUserIdAndDateVoting(Integer userId, LocalDate localDate);
}
