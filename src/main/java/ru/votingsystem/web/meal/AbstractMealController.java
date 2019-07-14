package ru.votingsystem.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.votingsystem.model.Meal;
import ru.votingsystem.service.MealService;

import java.util.List;

import static ru.votingsystem.util.ValidationUtil.checkNew;

@Controller
public class AbstractMealController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public List<Meal> getAll(int restaurantId) {
        log.info("getAll from {}", restaurantId);
        return service.getAll(restaurantId);
    }

    public Meal get(int id, int restaurantId) {
        log.info("get {} from {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    public void delete(int id, int restaurantId) {
        log.info("delete {} from {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    public void update(Meal meal, int restaurantId) {
        log.info("create {} for {}", meal, restaurantId);
        service.update(meal, restaurantId);
    }

    public Meal create(Meal meal, int restaurantId) {
        log.info("create {} from {}", meal, restaurantId);
        checkNew(meal);
        return service.create(meal, restaurantId);
    }

}
