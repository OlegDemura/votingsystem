package ru.votingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import ru.votingsystem.model.Restaurant;
import ru.votingsystem.repository.CrudRestaurantRepository;
import ru.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

import static ru.votingsystem.util.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    private final CrudRestaurantRepository repository;

    @Autowired
    public RestaurantService(CrudRestaurantRepository repository) {
        this.repository = repository;
    }

    public List<Restaurant> getAll() {
        return repository.findAll(SORT_NAME);
    }

    @Cacheable("restaurants")
    public List<Restaurant> getAllOnDate(LocalDate localDate) {
        return repository.getAllOnDate(localDate);
    }

    public Restaurant get(int restaurantId) throws NotFoundException {
        return checkNotFoundWithId(repository.findById(restaurantId)
                .orElseThrow(() -> new NotFoundException("Not found restaurant with id " + restaurantId)), restaurantId);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void delete(int restaurantId) throws NotFoundException {
        checkNotFoundWithId(repository.delete(restaurantId) != 0, restaurantId);
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public void update(Restaurant restaurant) throws NotFoundException {
        Assert.notNull(restaurant, "restaurant must not be null");
        checkNotFoundWithId(repository.save(restaurant), restaurant.getId());
    }

    @CacheEvict(value = "restaurants", allEntries = true)
    public Restaurant create(Restaurant restaurant) throws NotFoundException {
        Assert.notNull(restaurant, "restaurant must not be null");
        return repository.save(restaurant);
    }
}

