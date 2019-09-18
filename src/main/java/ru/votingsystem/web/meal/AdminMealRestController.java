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
    @GetMapping
    public List<Meal> getAll(@RequestParam int restaurantId) {
        return super.getAll(restaurantId);
    }

    @Override
    @GetMapping("/filter")
    public List<Meal> getAllOnDate(@RequestParam int restaurantId, @RequestParam(required = false) LocalDate date) {
        return super.getAllOnDate(restaurantId, date);
    }

    @Override
    @GetMapping("/{id}")
    public Meal get(@PathVariable int id, @RequestParam int restaurantId) {
        return super.get(id, restaurantId);
    }

    @Override
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id, @RequestParam int restaurantId) {
        super.delete(id, restaurantId);
    }

    @Override
    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody Meal meal, @RequestParam int restaurantId, @PathVariable int id) {
        assureIdConsistent(meal, id);
        super.update(meal, restaurantId, id);
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<Meal> createWithLocation(@RequestBody Meal meal, @RequestParam int restaurantId) {
        checkNew(meal);
        Meal created = super.create(meal, restaurantId);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL)
                .buildAndExpand(created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}
