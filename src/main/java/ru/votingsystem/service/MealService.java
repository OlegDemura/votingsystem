package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingsystem.model.Meal;
import ru.votingsystem.repository.DataJpaMealRepository;
import ru.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.votingsystem.util.DateTimeUtil.currentDate;
import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealService {

    private final DataJpaMealRepository repository;

    @Autowired
    public MealService(DataJpaMealRepository repository) {
        this.repository = repository;
    }

    public Meal get(int id, int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id, restaurantId), id);
    }

    @Cacheable("meals")
    public List<Meal> getAll(int restaurantId) {
        return repository.getAll(restaurantId);
    }

    @Cacheable("meals")
    public List<Meal> getAllOnDate(int restaurantId, @Nullable LocalDate date) {
        if (date == null) {
            return repository.getAll(restaurantId);
        }
        return repository.getAllOnDate(restaurantId, currentDate(date));
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void delete(int id, int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(id, restaurantId), id);
    }

    @CacheEvict(value = "meals", allEntries = true)
    public void update(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        checkNotFoundWithId(repository.save(meal, restaurantId), meal.getId());
    }

    @CacheEvict(value = "meals", allEntries = true)
    public Meal create(Meal meal, int restaurantId) {
        Assert.notNull(meal, "meal must not be null");
        return repository.save(meal, restaurantId);
    }
}

