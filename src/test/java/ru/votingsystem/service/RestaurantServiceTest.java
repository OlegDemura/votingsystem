package ru.votingsystem.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static ru.votingsystem.RestaurantTestData.*;

public class RestaurantServiceTest extends BaseServiceTest {

    @Autowired
    private RestaurantService service;

    @Test
    public void getAll() {
        List<Restaurant> all = service.getAll();
        assertMatch(all, RESTAURANT2, RESTAURANT1);
    }

    @Test
    public void get() {
        Restaurant restaurant = service.get(RESTAURANT1_ID);
        assertMatch(restaurant, RESTAURANT1);
    }

    @Test
    public void getNotFound() {
        thrown.expect(NotFoundException.class);
        service.get(1);
    }

    @Test
    public void delete() {
        service.delete(RESTAURANT1_ID);
        assertMatch(service.getAll(), RESTAURANT2);
    }

    @Test
    public void deleteNotFound() {
        thrown.expect(NotFoundException.class);
        service.delete(1);
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
}