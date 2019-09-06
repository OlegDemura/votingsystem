package ru.votingsystem.web.meal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.votingsystem.model.Meal;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static ru.votingsystem.util.ValidationUtil.assureIdConsistent;
import static ru.votingsystem.util.ValidationUtil.checkNew;

@RestController
@RequestMapping(value = AdminMealRestController.REST_URL, produces = APPLICATION_JSON_VALUE)
public class AdminMealRestController extends AbstractMealRestController {

    static final String REST_URL = "/rest/admin/meals";

    @Override
    @GetMapping("/{restaurantId}")
    public List<Meal> getAll(@PathVariable int restaurantId) {
        return super.getAll(restaurantId);
    }

    @Override
    @GetMapping("/{restaurantId}/filter")
    public List<Meal> getAllOnDate(@PathVariable int restaurantId, @RequestParam(required = false) LocalDate date) {
        return super.getAllOnDate(restaurantId, date);
    }

    @Override
    @GetMapping("/{restaurantId}/{id}")
    public Meal get(@PathVariable int id, @PathVariable int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{restaurantId}/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @PathVariable int restaurantId) {
        super.delete(id, restaurantId);
    }

    @Override
    @PutMapping(value = "/{restaurantId}/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @PathVariable int restaurantId, @PathVariable int id) {
        assureIdConsistent(meal, id);
        super.update(meal, restaurantId, id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE, value = "/{restaurantId}")
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @PathVariable int restaurantId) {
        checkNew(meal);
        Meal created = super.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{restaurantId}")
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
