package ru.votingsystem.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.votingsystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping(value = ProfileRestaurantRestController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class ProfileRestaurantRestController extends AbstractRestaurantRestController {

    static final String REST_URL = "/rest/profile/restaurants";

    @GetMapping
    public List<Restaurant> getAllOnDate() {
        return super.getAllOnDate();
    }

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable int id) {
        return super.get(id);
    }
}
