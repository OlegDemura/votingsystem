package ru.votingsystem.web.restaurant;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.votingsystem.model.Restaurant;

import java.util.List;

@RestController
@RequestMapping("/ajax/profile/restaurants")
public class RestaurantUIController extends AbstractRestaurantController{

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    public Restaurant getWithMeals(int id) {
        return super.getWithMeals(id);
    }
}
