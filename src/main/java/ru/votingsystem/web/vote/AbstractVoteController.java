package ru.votingsystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.votingsystem.model.Vote;
import ru.votingsystem.service.VoteService;
import ru.votingsystem.web.SecurityUtil;

import java.util.List;

import static ru.votingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.votingsystem.util.ValidationUtil.checkNew;

public abstract class AbstractVoteController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService service;

    public List<Vote> getAll(int restaurantId) {
        log.info("getAll");
        return service.getAll(restaurantId);
    }

    public Vote get(int id, int restaurantId) {
        log.info("get {} from {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        log.info("delete vote {} from {}", id, restaurantId);
        service.delete(id, userId, restaurantId);
    }

    public Vote create(Vote vote, int restaurant) {
        int userId = SecurityUtil.authUserId();
        checkNew(vote);
        log.info("create vote {} from {}", vote, restaurant);
        return service.create(vote, userId, restaurant);
    }

    public void update(Vote vote, int restaurant) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, restaurant);
        log.info("update vote {} from {}", vote, restaurant);
        service.update(vote, userId, restaurant);
    }

}
