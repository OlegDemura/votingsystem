package ru.votingsystem.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.service.RestaurantService;
import ru.votingsystem.web.AbstractControllerTest;
import ru.votingsystem.web.json.JsonUtil;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.RestaurantTestData.*;
import static ru.votingsystem.TestUtil.*;
import static ru.votingsystem.UserTestData.ADMIN;
import static ru.votingsystem.UserTestData.USER;

class RestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = RestaurantRestController.REST_URL + "/";

    @Autowired
    private RestaurantService service;

    @Test
    void testGetAll() throws Exception {
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT1));
    }

    @Test
    void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT2_ID)
                .with(userHttpBasic(USER)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(service.getAll(), RESTAURANT1);
    }

    @Test
    void testCreate() throws Exception {
        Restaurant expected = new Restaurant("New Restaurant", "New address");
        ResultActions action = mockMvc.perform(post(REST_URL)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJson(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(service.getAll(), expected, RESTAURANT2, RESTAURANT1);
    }

    @Test
    void testUpdate() throws Exception {
        Restaurant update = new Restaurant(RESTAURANT1.getId(), RESTAURANT1.getName(), RESTAURANT1.getAddress());
        update.setName("UpdateName");
        mockMvc.perform(put(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER))
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(update)))
                .andExpect(status().isNoContent());

        assertMatch(service.get(RESTAURANT1_ID), update);
    }
}