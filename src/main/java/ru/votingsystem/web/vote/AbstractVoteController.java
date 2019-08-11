package ru.votingsystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Vote;
import ru.votingsystem.service.VoteService;
import ru.votingsystem.web.SecurityUtil;

import java.time.LocalDate;
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

    public Vote get(int id, int restaurantId, LocalDate localDate) {
        log.info("get {} from {} where localDate {}", id, restaurantId, localDate);
        return service.get(id, restaurantId, localDate);
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

    public void update(Vote vote, int restaurantId) {
        int userId = SecurityUtil.authUserId();
        assureIdConsistent(vote, restaurantId);
        log.info("update vote {} from {}", vote, restaurantId);
        service.update(vote, userId, restaurantId);
    }

}
