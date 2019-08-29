package ru.votingsystem.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Vote;
import ru.votingsystem.util.exception.VoteRepeatException;


import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.UserTestData.ADMIN_ID;
import static ru.votingsystem.VoteTestData.*;

public class VoteServiceTest extends AbstractServiceTest {

    @Autowired
    private VoteService service;

    @Test
    void vote(){
        Vote voteCreate = getCreate();
        Vote newVote = service.vote(ADMIN_ID, RESTAURANT1_ID);
        voteCreate.setId(newVote.getId());
        assertMatch(voteCreate, newVote);
    }

    @Test
    void voteRepeat(){
        service.vote(ADMIN_ID, RESTAURANT1_ID);
        assertThrows(VoteRepeatException.class, ()->service.vote(ADMIN_ID, RESTAURANT2_ID));
    }
}