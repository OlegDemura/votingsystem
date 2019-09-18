package ru.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaRestaurantRepository {
    private static final Sort SORT_NAME = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public boolean delete(int restaurantId) {
        return crudRestaurantRepository.delete(restaurantId) != 0;
    }

    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public Restaurant get(int restaurantId) {
        return crudRestaurantRepository.findById(restaurantId).orElse(null);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME);
    }

    public List<Restaurant> getAllOnDate(LocalDate localDate) {
        return crudRestaurantRepository.getAllOnDate(localDate);
    }
}
