package ru.votingsystem;

import ru.votingsystem.model.Restaurant;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class RestaurantTestData {
    public static final int RESTAURANT1_ID = START_SEQ + 2;
    public static final int RESTAURANT2_ID = START_SEQ + 3;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT1_ID, "Москва", "г. Москва, ул. Ленина 28");
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT2_ID, "Дружба", "г. Санкт-Петербург, ул. Ким Чен Ына 18");

    public static Restaurant getUpdate(){return new Restaurant(RESTAURANT1_ID, "Обновленное название", RESTAURANT1.getAddress());}
    public static Restaurant getCreate(){return new Restaurant(null, "Новое название", "Новый адрес");}

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
