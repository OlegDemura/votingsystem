package ru.votingsystem.web.meal;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.votingsystem.model.Meal;
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
import static ru.votingsystem.UserTestData.USER;

class AdminMealRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = AdminMealRestController.REST_URL + "/";

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT1_ID))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGetAllOnRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL)
                .param("restaurantId", "1")
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + MEAL1_ID)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Meal.class), MEAL1));
    }

    @Test
    void getNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + 1)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void getUnauth() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + MEAL1_ID)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(mealService.getAll(RESTAURANT2_ID), MEAL3, MEAL2);
    }

    @Test
    void testDeleteForbidden() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void deleteNotFound() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(REST_URL + 1)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    void testUpdate() throws Exception {
        Meal update = new Meal(MEAL1.getId(), MEAL1.getDescription(), MEAL1.getPrice(), MEAL1.getDate());
        update.setDescription("UpdateDescription");
        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(update)))
                .andExpect(status().isNoContent());

        assertMatch(mealService.get(MEAL1_ID, RESTAURANT2_ID), update);
    }

    @Test
    void testUpdateForbidden() throws Exception {
        mockMvc.perform(put(REST_URL + RESTAURANT2_ID + "/" + MEAL1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }

    @Test
    void testCreate() throws Exception {
        Meal expected = new Meal("New Meal", 750, LocalDate.now());
        ResultActions action = mockMvc.perform(post(REST_URL)
                .param("restaurantId", String.valueOf(RESTAURANT2_ID))
                .with(userHttpBasic(ADMIN))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Meal returned = readFromJson(action, Meal.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(mealService.getAll(RESTAURANT2_ID), expected, MEAL1, MEAL3, MEAL2);
    }

    @Test
    void testCreateForbidden() throws Exception {
        mockMvc.perform(post(REST_URL + RESTAURANT2_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isForbidden());
    }
}