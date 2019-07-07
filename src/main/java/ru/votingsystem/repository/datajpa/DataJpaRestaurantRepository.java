package ru.votingsystem.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.votingsystem.model.Restaurant;

import java.util.List;

@Repository
public class DataJpaRestaurantRepository {

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public boolean delete(int restaurantId){
        return false;
    }

    public Restaurant save(Restaurant restaurant){
        return null;
    }

    public Restaurant get(int id){
        return null;
    }

    public List<Restaurant> getAll(){
        return null;
    }
}
