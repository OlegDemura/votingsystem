package ru.votingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Vote;
import ru.votingsystem.util.exception.VoteException;


import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.UserTestData.ADMIN_ID;
import static ru.votingsystem.UserTestData.USER_ID;
import static ru.votingsystem.VoteTestData.*;

class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void vote() {
        Vote voteCreate = getCreate();
        Vote newVote = service.vote(USER_ID, RESTAURANT1_ID, LocalTime.now());
        voteCreate.setId(newVote.getId());
        assertMatch(voteCreate, newVote);
    }

    @Test
    void voteRepeatPossible() {
        service.vote(USER_ID, RESTAURANT2_ID, LocalTime.of(9, 0));
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.of(10, 0));
    }

    @Test
    void voteRepeatImpossible() {
        service.vote(USER_ID, RESTAURANT1_ID, LocalTime.of(9, 0));
        assertThrows(VoteException.class, () -> service.vote(USER_ID, RESTAURANT2_ID, LocalTime.of(12, 0)));
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
                LocalDate.of(2019,7,6),
                LocalDate.of(2019,7,12));
        assertMatch(RESTAURANT1_COUNT, expected);
    }
}