package ru.votingsystem.repository;

import ru.votingsystem.model.Vote;

import java.util.List;


public interface VoteRepository {
    // null if not found, when updated
    Vote save(Vote vote, int userId, int restaurantId);

    // false if not found
    boolean delete(int id, int userId, int restaurantId);

    // null if not found
    Vote get(int id, int restaurantId);

    List<Vote> getAll(int restaurantId);
}
