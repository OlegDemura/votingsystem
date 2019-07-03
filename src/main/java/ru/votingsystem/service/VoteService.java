package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingsystem.model.Vote;
import ru.votingsystem.repository.VoteRepository;
import ru.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class VoteService {

    private final VoteRepository repository;

    @Autowired
    public VoteService(VoteRepository repository) {
        this.repository = repository;
    }

    public List<Vote> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    public Vote get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    public void delete(int id, int userId, int restaurantId) {
        checkNotFoundWithId(repository.delete(id, userId, restaurantId), id);
    }

    public void update(Vote vote, int userId, int restaurantId) {
        Assert.notNull(vote, "vote must not be null");
        checkNotFoundWithId(repository.save(vote, userId, restaurantId), vote.getId());
    }
}
