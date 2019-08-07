package ru.votingsystem.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import ru.votingsystem.model.Meal;
import ru.votingsystem.util.exception.NotFoundException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static ru.votingsystem.MealTestData.*;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1_ID;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2_ID;

public class MealServiceTest extends AbstractServiceTest {

    @Autowired
    private MealService service;

    @Autowired
    private CacheManager cacheManager;

    @BeforeEach
    public void setUp() throws Exception {
        cacheManager.getCache("meals").clear();
    }

    @Test
    public void get() {
        Meal meal = service.get(MEAL1_ID, RESTAURANT2_ID);
        assertMatch(meal, MEAL1);
    }

    @Test
    public void getNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.get(MEAL1_ID, RESTAURANT1_ID));
    }

    @Test
    public void getNotOwn() {
        assertThrows(NotFoundException.class, () ->
                service.get(MEAL1_ID, RESTAURANT1_ID));
    }

    @Test
    public void getAll() {
        List<Meal> list = service.getAll(RESTAURANT2_ID);
        assertMatch(list, MEALS);
    }

    @Test
    public void delete() {
        service.delete(MEAL3.getId(), RESTAURANT2_ID);
        assertMatch(service.getAll(RESTAURANT2_ID), MEAL1, MEAL2);
    }

    @Test
    public void deleteNotFound() {
        assertThrows(NotFoundException.class, () ->
                service.delete(1, RESTAURANT1_ID));
    }

    @Test
    public void deleteNotOwn() {
        assertThrows(NotFoundException.class, () ->
                service.delete(MEAL1_ID, RESTAURANT1_ID));
    }

    @Test
    public void update() {
        Meal mealUpdate = getUpdate();
        service.update(mealUpdate, RESTAURANT2_ID);
        assertMatch(service.get(MEAL1_ID, RESTAURANT2_ID), mealUpdate);
    }

    @Test
    public void updateNotFound() {
        NotFoundException e = assertThrows(NotFoundException.class, ()-> service.update(MEAL1, RESTAURANT1_ID));
        assertEquals(e.getMessage(), "Not found entity with id=" + MEAL1_ID);
    }

    @Test
    public void create() {
        Meal newMeal = getCreate();
        Meal created = service.create(newMeal, RESTAURANT2_ID);
        newMeal.setId(created.getId());
        assertMatch(newMeal, created);
        assertMatch(service.getAll(RESTAURANT2_ID), MEAL1, newMeal, MEAL3, MEAL2);
    }
}