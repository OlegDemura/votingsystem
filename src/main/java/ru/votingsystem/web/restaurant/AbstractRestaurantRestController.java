package ru.votingsystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.model.Vote;
import ru.votingsystem.service.RestaurantService;
import ru.votingsystem.service.VoteService;
import ru.votingsystem.to.RestaurantTo;

import java.time.LocalDate;
import java.util.List;

import static ru.votingsystem.util.RestaurantUtil.getAllRestaurantsWithCount;
import static ru.votingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.votingsystem.util.ValidationUtil.checkNew;

public abstract class AbstractRestaurantRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    protected RestaurantService service;

    @Autowired
    protected VoteService voteService;

    public List<Restaurant> getAll() {
        log.info("getAll");
        return service.getAll();
    }

    public List<RestaurantTo> getAllWithCountDateVoting(LocalDate dateVoting) {
        log.info("get restaurants with count votes on date = {}", dateVoting);
        List<Restaurant> restaurants = getAll();
        List<Vote> votes = voteService.getAllByDateVoting(dateVoting);
        return getAllRestaurantsWithCount(restaurants, votes, restaurant->true);
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
