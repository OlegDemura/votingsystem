package ru.votingsystem.web.restaurant;

import org.springframework.stereotype.Controller;
import ru.votingsystem.model.Restaurant;

import java.util.List;

@Controller
public class RestaurantRestController extends AbstractRestaurantController {

    @Override
    public List<Restaurant> getAll() {
        return super.getAll();
    }

    @Override
    public Restaurant get(int id) {
        return super.get(id);
    }

    @Override
    public void delete(int id) {
        super.delete(id);
    }

    @Override
    public Restaurant create(Restaurant restaurant) {
        return super.create(restaurant);
    }

    @Override
    public void update(Restaurant restaurant, int id) {
        super.update(restaurant, id);
    }
}
