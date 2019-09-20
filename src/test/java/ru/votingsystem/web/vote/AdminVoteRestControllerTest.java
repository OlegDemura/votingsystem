package ru.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import ru.votingsystem.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.TestUtil.userHttpBasic;
import static ru.votingsystem.UserTestData.ADMIN;
import static ru.votingsystem.UserTestData.USER;

class AdminVoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = AdminVoteRestController.REST_URL + "/";

    @Test
    void testCountAllByRestaurantIdAndDateVoting() throws Exception {
        mockMvc.perform(get(REST_URL + "count")
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .param("localDate", "2019-07-06")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "countwithfilter")
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .param("startDate", "2019-07-06")
                .param("endDate", "2019-07-08")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + "count")
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .param("localDate", "2019-07-06")
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingBetweenForbidden() throws Exception {
        mockMvc.perform(get(REST_URL + "countwithfilter")
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .param("startDate", "2019-07-06")
                .param("endDate", "2019-07-08")
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL + "count")
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .param("localDate", "2019-07-06"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingBetweenUnAuth() throws Exception {
        mockMvc.perform(get(REST_URL + "countwithfilter")
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .param("startDate", "2019-07-06")
                .param("endDate", "2019-07-08"))
                .andExpect(status().isUnauthorized());
    }
}
