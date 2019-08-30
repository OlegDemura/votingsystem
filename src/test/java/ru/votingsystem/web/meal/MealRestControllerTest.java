package ru.votingsystem.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import ru.votingsystem.model.Meal;
import ru.votingsystem.service.MealService;
import ru.votingsystem.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.MealTestData.*;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.TestUtil.readFromJsonMvcResult;

class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + "/";

    @Autowired
    private MealService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Meal.class), MEAL1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(RESTAURANT2_ID), MEAL3, MEAL2);
    }

    @Test
    void testUpdate() throws Exception {

    }

    @Test
    void testCreate() throws Exception {
    }
}