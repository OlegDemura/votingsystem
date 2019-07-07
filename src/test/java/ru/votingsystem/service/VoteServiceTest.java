package ru.votingsystem.service;

import org.assertj.core.condition.Not;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Vote;
import ru.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.UserTestData.ADMIN_ID;
import static ru.votingsystem.UserTestData.USER_ID;
import static ru.votingsystem.VoteTestData.*;

public class VoteServiceTest extends BaseServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void getAll() {
        List<Vote> all = service.getAll(RESTAURANT1_ID);
        assertMatch(all, VOTE3, VOTE2);
    }

    @Test
    public void get() {
        Vote vote = service.get(VOTE1_ID, RESTAURANT2_ID);
        assertMatch(vote, VOTE1);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(1, RESTAURANT1_ID);
    }

    @Test
    public void getNotOwn() {
        thrown.expect(NotFoundException.class);
        service.get(VOTE1_ID, RESTAURANT1_ID);
    }

    @Test
    public void delete() {
        service.delete(VOTE2_ID, ADMIN_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), VOTE3);
    }

    @Test
    public void deleteNotOwn() {
        thrown.expect(NotFoundException.class);
        service.delete(VOTE1_ID, ADMIN_ID, RESTAURANT1_ID);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(1, ADMIN_ID, RESTAURANT1_ID);
    }

    @Test
    public void update() {
        Vote voteUpdate = getUpdate();
        service.update(voteUpdate, ADMIN_ID, RESTAURANT1_ID);
        assertMatch(service.get(voteUpdate.getId(), RESTAURANT1_ID), voteUpdate);
    }

    @Test
    public void updateNotFound() {
        thrown.expect(NotFoundException.class);
        Vote voteUpdate = getUpdate();
        service.update(voteUpdate, ADMIN_ID, RESTAURANT2_ID);
    }

    @Test
    public void updateNotOwn() {
        thrown.expect(NotFoundException.class);
        Vote voteUpdate = getUpdate();
        service.update(voteUpdate, USER_ID, RESTAURANT2_ID);
    }

    @Test
    public void create() {
        Vote voteCreate = getCreate();
        Vote newVote = service.create(voteCreate, USER_ID, RESTAURANT1_ID);
        voteCreate.setId(newVote.getId());
        assertMatch(service.getAll(RESTAURANT1_ID), newVote, VOTE3, VOTE2);
    }
}