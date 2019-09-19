package ru.votingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.VoteTestData;
import ru.votingsystem.model.Vote;
import ru.votingsystem.util.exception.NotFoundException;
import ru.votingsystem.util.exception.VoteException;


import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.UserTestData.*;
import static ru.votingsystem.VoteTestData.*;
import static ru.votingsystem.RestaurantTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void vote() {
        Vote voteCreate = VoteTestData.getCreate();
        Vote newVote = service.vote(USER_ID, RESTAURANT1_ID, LocalTime.now());
        voteCreate.setId(newVote.getId());
        assertMatch(voteCreate, newVote);
    }

    @Test
    void voteRepeatPossible() {
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.of(9, 0));
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.of(10, 0));
    }

    @Test
    void voteRepeatImpossible() {
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.of(9, 0));
        assertThrows(VoteException.class, () -> service.vote(USER_ID, RESTAURANT1_ID, LocalTime.of(12, 0)));
    }

    @Test
    void voteRepeatOnRestaurantWithoutMeals() {
        assertThrows(NotFoundException.class, () -> service.vote(USER_ID, RESTAURANT2_ID, LocalTime.of(9,0)));
    }

    @Test
    void countAllByRestaurantIdAndDateVoting() {
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.now());
        service.vote(ADMIN_ID, RESTAURANT1_ID, LocalTime.now());
        Integer expected = service.countAllByRestaurantIdAndDateVoting(RESTAURANT1_ID, LocalDate.now());
        assertMatch(2, expected);
    }

    @Test
    void countAllByRestaurantIdAndDateVotingBetween() {
        Integer expected = service.countAllByRestaurantIdAndDateVotingBetween(RESTAURANT1_ID,
                LocalDate.of(2019, 7, 6),
                LocalDate.of(2019, 7, 12));
        assertMatch(RESTAURANT1_COUNT, expected);
    }

    @Test
    void getAllByDateVoting() {
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.now());
        service.vote(ADMIN_ID, RESTAURANT1_ID, LocalTime.now());

        List<Vote> list = service.getAllByDateVoting(LocalDate.now());
        assertMatch(list.size(), 2);
    }
}