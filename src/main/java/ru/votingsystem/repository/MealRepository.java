package ru.votingsystem.repository;

import ru.votingsystem.model.Meal;

import java.util.List;

public interface MealRepository{
    // null if not found, when updated
    Meal save(Meal meal);

    // false if not found
    boolean delete(int id);

    // null if not found
    Meal get(int id);

    List<Meal> getAll();
}