package ru.votingsystem.web.vote;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.service.VoteService;
import ru.votingsystem.web.AbstractControllerTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.TestUtil.userHttpBasic;
import static ru.votingsystem.UserTestData.USER;
import static ru.votingsystem.web.SecurityUtil.authUserId;


class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    void testVoting() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT2_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk());
        service.countAllByRestaurantIdAndDateVoting(RESTAURANT2_ID, LocalDate.now());
    }

    @Test
    void testCountAllByRestaurantIdAndDateVoting() throws Exception {
        mockMvc.perform(get(REST_URL + "count/" + RESTAURANT2_ID)
                .param("localDate", "2019-07-06")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "countwithfilter/" + RESTAURANT2_ID)
                .param("startDate", "2019-07-06")
                .param("endDate", "2019-07-08")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
}