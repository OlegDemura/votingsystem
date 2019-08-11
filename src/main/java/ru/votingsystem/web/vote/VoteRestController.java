package ru.votingsystem.web.vote;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingsystem.model.Vote;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping
public class VoteRestController extends AbstractVoteController {
    static final String REST_URL = "/rest/restaurant/votes";

    @Override
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

}
