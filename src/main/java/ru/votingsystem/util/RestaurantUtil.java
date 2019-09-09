package ru.votingsystem.util;

import ru.votingsystem.model.Restaurant;
import ru.votingsystem.model.Vote;
import ru.votingsystem.to.RestaurantTo;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class RestaurantUtil {

    public static List<RestaurantTo> getAllRestaurantsWithCount(List<Restaurant> restaurants, List<Vote> votes, Predicate<Restaurant> filter) {

        Map<Restaurant, Long> map = votes.stream()
                .collect(Collectors.groupingBy(Vote::getRestaurant, Collectors.counting()));

        return restaurants.stream()
                .filter(filter)
                .map(restaurant -> getRestaurantTo(restaurant, map.get(restaurant))).collect(Collectors.toList());
    }

    private static RestaurantTo getRestaurantTo(Restaurant restaurant, Long amount) {
        return new RestaurantTo(restaurant.getId(),
                restaurant.getName(),
                restaurant.getAddress(),
                Math.toIntExact(Objects.requireNonNullElse(amount,0L)));
    }
}
