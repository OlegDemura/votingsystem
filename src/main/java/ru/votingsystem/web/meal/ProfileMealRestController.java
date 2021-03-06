package ru.votingsystem.web.meal;

import org.springframework.web.bind.annotation.*;
import ru.votingsystem.model.Meal;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.votingsystem.util.DateTimeUtil.currentDate;

@RestController
@RequestMapping(value = ProfileMealRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class ProfileMealRestController extends AbstractMealRestController {

    static final String REST_URL = "/rest/profile/meals";

    @GetMapping
    public List<Meal> getAllOnDate(@RequestParam int restaurantId) {
        return super.getAllOnDate(restaurantId, currentDate());
    }
}
