package ru.votingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Vote;
import ru.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.UserTestData.ADMIN_ID;
import static ru.votingsystem.UserTestData.USER_ID;
import static ru.votingsystem.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    public void getAll() {
        List<Vote> all = service.getAll(RESTAURANT1_ID);
        assertMatch(all, VOTE3, VOTE2);
    }

    @Test
    public void get() {
        Vote vote = service.get(VOTE1_ID, RESTAURANT2_ID, LocalDate.of(2019, 6, 7));
        assertMatch(vote, VOTE1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(1, RESTAURANT1_ID, LocalDate.of(2019, 6, 7)));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () ->
                service.get(VOTE1_ID, RESTAURANT1_ID, LocalDate.of(2019, 6, 7)));
    }

    @Test
    public void delete() {
        service.delete(VOTE2_ID, ADMIN_ID, RESTAURANT1_ID);
        assertMatch(service.getAll(RESTAURANT1_ID), VOTE3);
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () ->
                service.delete(VOTE1_ID, ADMIN_ID, RESTAURANT1_ID));
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(1, ADMIN_ID, RESTAURANT1_ID));
    }

    @Test
    public void update() {
        Vote voteUpdate = getUpdate();
        service.update(voteUpdate, ADMIN_ID, RESTAURANT1_ID);
        assertMatch(service.get(voteUpdate.getId(), RESTAURANT1_ID, LocalDate.of(2019, 6, 7)), voteUpdate);
    }

    @Test
    public void updateNotFound() {
        Vote voteUpdate = getUpdate();
        assertThrows(NotFoundException.class, () ->
                service.update(voteUpdate, ADMIN_ID, RESTAURANT2_ID));
    }

    @Test
    public void updateNotOwn() {
        Vote voteUpdate = getUpdate();
        assertThrows(NotFoundException.class, () ->
                service.update(voteUpdate, USER_ID, RESTAURANT2_ID));
    }

    @Test
    public void create() {
        Vote voteCreate = getCreate();
        Vote newVote = service.create(voteCreate, USER_ID, RESTAURANT1_ID);
        voteCreate.setId(newVote.getId());
        assertMatch(service.getAll(RESTAURANT1_ID), newVote, VOTE3, VOTE2);
    }
}