package ru.votingsystem.web.meal;

import org.springframework.web.bind.annotation.*;
import ru.votingsystem.model.Meal;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ProfileMealRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class ProfileMealRestController extends AbstractMealRestController {

    static final String REST_URL = "/rest/profile/meals";

    @GetMapping("/{restaurantId}")
    public List<Meal> getAll(@PathVariable int restaurantId) {
        log.info("getAll from {}", restaurantId);
        return super.getAll(restaurantId);
    }

    @GetMapping("/{restaurantId}/{id}")
    public Meal get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get {} from {}", id, restaurantId);
        return super.get(id, restaurantId);
    }
}
