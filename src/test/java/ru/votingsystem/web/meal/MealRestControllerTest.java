package ru.votingsystem.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.votingsystem.model.Meal;
import ru.votingsystem.service.MealService;
import ru.votingsystem.web.AbstractControllerTest;
import ru.votingsystem.web.json.JsonUtil;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.MealTestData.*;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;
import static ru.votingsystem.TestUtil.*;
import static ru.votingsystem.UserTestData.ADMIN;

class MealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = MealRestController.REST_URL + "/";

    @Autowired
    private MealService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Meal.class), MEAL1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(RESTAURANT2_ID), MEAL3, MEAL2);
    }

    @Test
    void testUpdate() throws Exception {
        Meal update = new Meal(MEAL1.getId(), MEAL1.getDescription(), MEAL1.getPrice(), MEAL1.getDate());
        update.setDescription("UpdateDescription");
        mockMvc.perform(put(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(update)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(MEAL1_ID, RESTAURANT2_ID), update);
    }

    @Test
    void testCreate() throws Exception {
        Meal expected = new Meal("New Meal", 750, LocalDate.now());
        ResultActions action = mockMvc.perform(post(REST_URL + RESTAURANT2_ID)
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(service.getAll(RESTAURANT2_ID), expected, MEAL1, MEAL3, MEAL2);
    }
}