package ru.votingsystem.service;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.util.exception.NotFoundException;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.RestaurantTestData.*;

public class RestaurantServiceTest extends AbstractServiceTest {

    @Autowired
    private RestaurantService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("restaurants").clear();
    }

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT2, RESTAURANT1);
    }

    @Test
    public void getAllOnDate() {
        List<Restaurant> all = service.getAllOnDate(LocalDate.now());
        assertMatch(all, RESTAURANT1);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () -> service.get(1));
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(1));
    }

    @Test
    public void update() {
        Restaurant restaurantUpdate = getUpdate();
        service.update(restaurantUpdate);
        assertMatch(service.get(RESTAURANT1_ID), restaurantUpdate);
    }


    @Test
    public void create() {
        Restaurant restaurantNew = getCreate();
        service.create(restaurantNew);
        assertMatch(service.getAll(), RESTAURANT2, RESTAURANT1, restaurantNew);
    }

    @Test
    void createWithException() throws Exception {
        validateRootCause(() -> service.create(new Restaurant(null, null, "New address")), ConstraintViolationException.class);
        validateRootCause(() -> service.create(new Restaurant(null, "New Restaurant", null)), ConstraintViolationException.class);
    }
}