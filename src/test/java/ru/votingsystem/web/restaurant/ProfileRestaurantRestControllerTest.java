package ru.votingsystem.web.restaurant;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.web.AbstractControllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.votingsystem.RestaurantTestData.*;
import static ru.votingsystem.TestUtil.readFromJsonMvcResult;
import static ru.votingsystem.TestUtil.userHttpBasic;
import static ru.votingsystem.UserTestData.USER;

class ProfileRestaurantRestControllerTest extends AbstractControllerTest {

    private static final String REST_URL = ProfileRestaurantRestController.REST_URL + "/";

    @Test
    void testGetAll() throws Exception{
        mockMvc.perform(get(REST_URL).with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void testGet() throws Exception{
        mockMvc.perform(get(REST_URL + RESTAURANT1_ID)
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(result -> assertMatch(readFromJsonMvcResult(result, Restaurant.class), RESTAURANT1));
    }
}