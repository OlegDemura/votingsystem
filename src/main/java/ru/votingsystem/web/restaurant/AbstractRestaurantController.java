package ru.votingsystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.service.RestaurantService;
import ru.votingsystem.service.VoteService;
import java.util.List;

import static ru.votingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.votingsystem.util.ValidationUtil.checkNew;

abstract class AbstractRestaurantController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantService service;

    @Autowired
    protected VoteService voteService;

    public List<Restaurant> getAll() {
        return service.getAll();
    }

    public Restaurant get(int id) {
        log.info("get {}", id);
        return service.get(id);
    }

    public void delete(int id) {
        log.info("delete {}", id);
        service.delete(id);
    }

    public Restaurant create(Restaurant restaurant) {
        log.info("create {}", restaurant);
        checkNew(restaurant);
        return service.create(restaurant);
    }

    public void update(Restaurant restaurant, int id) {
        log.info("update {} with {}", restaurant, id);
        assureIdConsistent(restaurant, id);
        service.update(restaurant);
    }
}
