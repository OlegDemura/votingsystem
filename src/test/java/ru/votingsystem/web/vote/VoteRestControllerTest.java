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
import static ru.votingsystem.web.SecurityUtil.authUserId;


class VoteRestControllerTest extends AbstractControllerTest {
    private static final String REST_URL = VoteRestController.REST_URL + "/";

    @Autowired
    private VoteService service;

    @Test
    void testVoting() throws Exception {
        mockMvc.perform(post(REST_URL + RESTAURANT2_ID))
                .andExpect(status().isOk());
        service.countAllByRestaurantIdAndDateVoting(RESTAURANT2_ID, LocalDate.now());
    }

    @Test
    void testDeleteVoteByUserIdAndDateVoting() throws Exception {
        service.vote(authUserId(), RESTAURANT2_ID);
        mockMvc.perform(delete(REST_URL))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    void testCountAllByRestaurantIdAndDateVoting() throws Exception {
        mockMvc.perform(get(REST_URL + "count/" + RESTAURANT2_ID)
                .param("localDate", "2019-07-06"))
                .andExpect(status().isOk())
                .andExpect(content().string("1"));
    }

    @Test
    void testCountAllByRestaurantIdAndDateVotingBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "countwithfilter/" + RESTAURANT1_ID)
                .param("startDate", "2019-07-06")
                .param("endDate", "2019-07-08"))
                .andExpect(status().isOk())
                .andExpect(content().string("2"));
    }
}