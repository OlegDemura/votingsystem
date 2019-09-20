package ru.votingsystem.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalTime;

import static ru.votingsystem.web.SecurityUtil.authUserId;

@RestController
@RequestMapping(UserVoteRestController.REST_URL)
public class UserVoteRestController extends AbstractVoteController{
    static final String REST_URL = "/rest/profile/vote";

    @GetMapping
    @ResponseStatus(value = HttpStatus.OK)
    public void voting(@RequestParam int restaurantId) {
        log.info("voting for restaurantId {} from userId {}", restaurantId, authUserId());
        service.vote(authUserId(), restaurantId, LocalTime.now());
    }
}
