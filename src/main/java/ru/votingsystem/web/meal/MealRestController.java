package ru.votingsystem.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingsystem.model.Meal;
import ru.votingsystem.service.MealService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.votingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = MealRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class MealRestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    private MealService service;

    @Autowired
    public MealRestController(MealService service) {
        this.service = service;
    }

    static final String REST_URL = "/rest/restaurant/meals";

    @GetMapping("/{restaurantId}")
    public List<Meal> getAll(@PathVariable int restaurantId) {
        log.info("getAll from {}", restaurantId);
        return service.getAll(restaurantId);
    }

    @GetMapping("/{restaurantId}/{id}")
    public Meal get(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("get {} from {}", id, restaurantId);
        return service.get(id, restaurantId);
    }

    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        log.info("delete {} from {}", id, restaurantId);
        service.delete(id, restaurantId);
    }

    @PutMapping(value = "/{restaurantId}/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int restaurantId, @PathVariable int id) {
        log.info("create {} for {}", meal, restaurantId);
        assureIdConsistent(meal, id);
        service.update(meal, restaurantId);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/{restaurantId}")
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable int restaurantId) {
        log.info("create {} from {}", meal, restaurantId);
        checkNew(meal);
        Meal created = service.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
