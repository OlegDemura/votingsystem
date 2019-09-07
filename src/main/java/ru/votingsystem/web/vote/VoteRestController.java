package ru.votingsystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.votingsystem.service.VoteService;

import java.time.LocalDate;
import java.time.LocalTime;

import static ru.votingsystem.util.DateTimeUtil.*;
import static ru.votingsystem.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/vote";

    @Autowired
    private VoteService service;

    @GetMapping(value = "/{restaurantId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void voting(@PathVariable int restaurantId) {
        log.info("voting for restaurantId {} from userId {}", restaurantId, authUserId());
        service.vote(authUserId(), restaurantId, LocalTime.now());
    }

    @GetMapping(value = "/count/{restaurantId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Integer countAllByRestaurantIdAndDateVoting(@PathVariable int restaurantId, @RequestParam LocalDate localDate){
        log.info("count for restaurant {} for date {}", restaurantId, localDate);
        return service.countAllByRestaurantIdAndDateVoting(restaurantId, localDate);
    }

    @GetMapping(value = "/countwithfilter/{restaurantId}")
    @ResponseStatus(value = HttpStatus.OK)
    public Integer countAllByRestaurantIdAndDateVotingBetween(@PathVariable int restaurantId,
                                                           @RequestParam(required = false) LocalDate startDate,
                                                           @RequestParam(required = false) LocalDate endDate){
        log.info("count for restaurant {} between dates {} and {}", restaurantId, startDate, endDate);
        return service.countAllByRestaurantIdAndDateVotingBetween(restaurantId, startDate, endDate);
    }
}
