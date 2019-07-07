package ru.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.votingsystem.model.Vote;

import java.util.List;

@Repository
public class DataJpaVoteRepository {

    @Autowired
    private CrudVoteRepository repository;

    public Vote save(Vote vote, int userId, int restaurantId){
        return null;
    }

    public boolean delete(int id, int userId, int restaurantId){
        return false;
    }

    public Vote get(int id, int restaurantId){
        return null;
    }

    public List<Vote> getAll(int restaurantId){
        return null;
    }
}
