package ru.votingsystem.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingsystem.model.Vote;
import ru.votingsystem.service.VoteService;

import java.net.URI;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.votingsystem.util.DateTimeUtil.*;
import static ru.votingsystem.util.ValidationUtil.checkNew;
import static ru.votingsystem.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(VoteRestController.REST_URL)
public class VoteRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    static final String REST_URL = "/rest/vote";

    private VoteService service;

    @Autowired
    public VoteRestController(VoteService service) {
        this.service = service;
    }

    @GetMapping(value = "/{restaurantId}")
    @ResponseStatus(value = HttpStatus.OK)
    public void voting(@PathVariable int restaurantId) {
        log.info("voting for restaurantId {} from userId {}", restaurantId, authUserId());
        service.vote(authUserId(), restaurantId);
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteVoteByUserIdAndDateVoting(){
        log.info("voting deleted for current date {}", currentDate());
        service.deleteVoteByUserIdAndDateVoting(authUserId(), currentDate());
    }

    @GetMapping(value = "/{restaurantId}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public Integer countAllByRestaurantIdAndDateVoting(@PathVariable int restaurantId, @RequestParam LocalDate localDate){
        //TODO Need think
        log.info("count all");
        return service.countAllByRestaurantIdAndDateVoting(restaurantId, localDate);
    }

    @GetMapping(value = "/filter/{restaurantId}", consumes = APPLICATION_JSON_VALUE)
    public Integer countAllByRestaurantIdAndDateVotingBetween(@PathVariable int restaurantId,
                                                           @RequestParam(required = false) LocalDate startDate,
                                                           @RequestParam(required = false) LocalDate endDate){
        //TODO Need think
        return service.countAllByRestaurantIdAndDateVotingBetween(restaurantId, startDate, endDate);
    }

    /*@Override
    @GetMapping(value = "/{restaurantId}", consumes = APPLICATION_JSON_VALUE)
    public List<Vote> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @GetMapping(value = "/{restaurantId}/{id}", consumes = APPLICATION_JSON_VALUE)
    public Vote get(@PathVariable int id, @PathVariable int restaurantId) {
        return super.get(id, restaurantId, LocalDate.now());
    }

    @Override
    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        if (LocalTime.now().isBefore(DEFAULT_EXPIRED_TIME))
            super.delete(id, restaurantId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/{restaurantId}")
    public ResponseEntity<Vote> createWithLocation(@RequestBody Vote vote, @PathVariable int restaurantId) {
        checkNew(vote);
        Vote created = super.create(vote, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}/{id}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @Override
    @PutMapping(value = "/{restaurantId}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Vote vote, @PathVariable int restaurantId) {
        if (LocalTime.now().isBefore(LocalTime.of(11,0))) {
            super.update(vote, restaurantId);
        }
    }*/
}
