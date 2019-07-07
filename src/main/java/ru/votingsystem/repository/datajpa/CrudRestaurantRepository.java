package ru.votingsystem.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.votingsystem.model.Restaurant;

public interface CrudRestaurantRepository extends JpaRepository<Restaurant, Integer> {


}
