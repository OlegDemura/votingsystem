package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingsystem.model.Meal;
import ru.votingsystem.repository.CrudMealRepository;
import ru.votingsystem.repository.CrudRestaurantRepository;
import ru.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.votingsystem.util.DateTimeUtil.currentDate;
import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final CrudMealRepository repository;

    private final CrudRestaurantRepository restaurantRepository;

    @Autowired
    public MealService(CrudMealRepository repository, CrudRestaurantRepository restaurantRepository) {
        this.repository = repository;
        this.restaurantRepository = restaurantRepository;
    }

    public Meal get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.getOneMeal(id, restaurantId), id);
    }

    public List<Meal> getAll(int restaurantId) {
        return checkNotFoundWithId(repository.getAll(restaurantId), restaurantId);
    }

    @Cacheable("meals")
    public List<Meal> getAllOnDate(int restaurantId, @Nullable LocalDate date) {
        return checkNotFoundWithId(repository.getAllOnDate(restaurantId, currentDate(date)), restaurantId);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId) != 0, id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        meal.setRestaurant(get(meal.id(), restaurantId).getRestaurant());
        repository.save(meal);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        meal.setRestaurant(restaurantRepository.getOne(restaurantId));
        return repository.save(meal);
    }
}

