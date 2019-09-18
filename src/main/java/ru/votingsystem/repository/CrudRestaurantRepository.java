package ru.votingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.votingsystem.model.Restaurant;

import java.time.LocalDate;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Restaurant r where r.id=:id")
    int delete(@Param("id") int restaurantId);

    @Query("SELECT DISTINCT r FROM Restaurant r JOIN FETCH r.meals m WHERE m.date = ?1")
    List<Restaurant> getAllOnDate(@Param("localDate") LocalDate localDate);
}
