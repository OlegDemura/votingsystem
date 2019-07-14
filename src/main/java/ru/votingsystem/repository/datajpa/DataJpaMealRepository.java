package ru.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.votingsystem.model.Meal;

import java.util.List;

@Repository
public class DataJpaMealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public Meal save(Meal meal, int restaurantId) {
        if (!meal.isNew() && get(meal.getId(), restaurantId) == null) {
            return null;
        }

        meal.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        return crudMealRepository.save(meal);
    }

    public Meal get(int id, int restaurantId) {
        return crudMealRepository.getOneMeal(id, restaurantId);
    }

    public List<Meal> getAll(int restaurantId) {
        return crudMealRepository.getAll(restaurantId);
    }

    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.deleteOne(id, restaurantId) != 0;
    }
}