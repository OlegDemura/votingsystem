package ru.votingsystem.repository;

import ru.votingsystem.model.Meal;

import java.util.List;

public interface MealRepository{
    // null if not found, when updated
    Meal save(Meal meal, int restaurantId);

    // false if not found
    boolean delete(int id, int restaurantId);

    // null if not found
    Meal get(int id, int restaurantId);

    List<Meal> getAll(int restaurantId);
}
