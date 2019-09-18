package ru.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Meal;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Meal m WHERE m.id = :id AND m.restaurant.id = :restaurantId")
    int delete(@Param("id") int id, @Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id = :restaurantId ORDER BY m.description")
    List<Meal> getAll(@Param("restaurantId") int restaurantId);

    @Query("SELECT m FROM Meal m WHERE m.restaurant.id = :restaurantId AND m.date = :date ORDER BY m.description")
    List<Meal> getAllOnDate(@Param("restaurantId") int restaurantId, @Param("date") LocalDate date);

    @Query("SELECT m FROM Meal m WHERE m.id = :id AND m.restaurant.id = :restaurantId")
    Meal getOneMeal(@Param("id") int id, @Param("restaurantId") int restaurantId);

}

