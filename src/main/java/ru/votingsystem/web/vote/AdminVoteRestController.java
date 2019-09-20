package ru.votingsystem.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping(AdminVoteRestController.REST_URL)
public class AdminVoteRestController extends AbstractVoteController{
    static final String REST_URL = "/rest/admin/vote";

    @GetMapping(value = "/count")
    @ResponseStatus(value = HttpStatus.OK)
    public Integer countAllByRestaurantIdAndDateVoting(@RequestParam int restaurantId, @RequestParam LocalDate localDate){
        log.info("count for restaurant {} for date {}", restaurantId, localDate);
        return service.countAllByRestaurantIdAndDateVoting(restaurantId, localDate);
    }

    @GetMapping(value = "/countwithfilter")
    @ResponseStatus(value = HttpStatus.OK)
    public Integer countAllByRestaurantIdAndDateVotingBetween(@RequestParam int restaurantId,
                                                              @RequestParam(required = false) LocalDate startDate,
                                                              @RequestParam(required = false) LocalDate endDate){
        log.info("count for restaurant {} between dates {} and {}", restaurantId, startDate, endDate);
        return service.countAllByRestaurantIdAndDateVotingBetween(restaurantId, startDate, endDate);
    }
}
