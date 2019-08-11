package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingsystem.model.Vote;
import ru.votingsystem.repository.datajpa.DataJpaVoteRepository;
import ru.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {
    private final DataJpaVoteRepository repository;

    @Autowired
    public VoteService(DataJpaVoteRepository repository) {
        this.repository = repository;
    }

    public List<Vote> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public Vote get(int id, int restaurantId, LocalDate localDate) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId, localDate), id);
    }

    public void delete(int id, int userId, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, userId, restaurantId), id);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }

    public Vote create(Vote vote, int userId, int restaurant) {
        Assert.notNull(vote, "vote must not be null");
        return repository.save(vote, userId, restaurant);
    }

    public Integer count(int restaurantId) {
        return repository.countAllByDateVoting(restaurantId);
    }
}
