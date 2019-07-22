package ru.votingsystem.web.restaurant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.model.Vote;
import ru.votingsystem.service.RestaurantService;
import ru.votingsystem.service.VoteService;
import ru.votingsystem.web.SecurityUtil;
import java.time.LocalDateTime;
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

    public void voteForRestaurant(int id){
        log.info("vote for restaurant {}", id);
        voteService.create(new Vote(LocalDateTime.now()), SecurityUtil.authUserId(), id);
    }

    public void deleteVoteForRestaurant(int voteId, int restaurantId){
        log.info("delete vote {} for restaurant {}", voteId, restaurantId);
        voteService.delete(voteId, SecurityUtil.authUserId(), restaurantId);
    }
}
