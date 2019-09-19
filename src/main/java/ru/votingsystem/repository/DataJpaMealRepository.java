package ru.votingsystem.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Meal;
import ru.votingsystem.util.exception.NotFoundException;

import java.time.LocalDate;
import java.util.List;

@Repository
public class DataJpaMealRepository {

    @Autowired
    private CrudMealRepository crudMealRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Transactional
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
        List<Meal> meals = crudMealRepository.getAll(restaurantId);
        if (meals.isEmpty()){
            throw new NotFoundException("Restaurant with id "+ restaurantId + " not found");
        }
        return meals;
    }

    public List<Meal> getAllOnDate(int restaurantId, LocalDate date) {
        List<Meal> meals = crudMealRepository.getAllOnDate(restaurantId, date);
        if (meals.isEmpty()){
            throw new NotFoundException("Restaurant with id "+ restaurantId + " not found");
        }
        return meals;
    }

    public boolean delete(int id, int restaurantId) {
        return crudMealRepository.delete(id, restaurantId) != 0;
    }
}
