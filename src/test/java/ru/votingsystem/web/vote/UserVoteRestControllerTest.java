package ru.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import ru.votingsystem.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.TestUtil.userHttpBasic;
import static ru.votingsystem.UserTestData.USER;


class UserVoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = UserVoteRestController.REST_URL + "/";

    @Test
    void testVoting() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT1_ID))
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
    }

    @Test
    void testVotingOnRestaurantWithoutMeal() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(USER)))
                .andExpect(status().isUnprocessableEntity())
                .andDo(print());
    }
}