package ru.votingsystem.web.meal;

import org.springframework.web.bind.annotation.*;
import ru.votingsystem.model.Meal;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = ProfileMealRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class ProfileMealRestController extends AbstractMealRestController {

    static final String REST_URL = "/rest/profile/meals";

    /*@Override
    @GetMapping("/{restaurantId}")
    public List<Meal> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }*/

    @Override
    @GetMapping("/{restaurantId}")
    public List<Meal> getAllOnDate(@PathVariable int restaurantId, @RequestParam(required = false) LocalDate date) {
        return super.getAllOnDate(restaurantId, date);
    }

    @Override
    @GetMapping("/{restaurantId}/{id}")
    public Meal get(@PathVariable int id, @PathVariable int restaurantId) {
        return super.get(id, restaurantId);
    }
}
