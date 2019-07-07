package ru.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import ru.votingsystem.model.Restaurant;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name");

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public boolean delete(int restaurantId) {
        return crudRestaurantRepository.delete(restaurantId) != 0;
    }

    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(SORT_NAME_EMAIL);
    }
}
