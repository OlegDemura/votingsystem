package ru.votingsystem;

import ru.votingsystem.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static ru.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 4;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, "Макароны", 500.5F, LocalDateTime.of(2015, 5, 30, 10, 0));
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, "Шашлык", 1000F, LocalDateTime.of(2015, 5, 30, 10, 0));
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, "Суп", 500F, LocalDateTime.of(2015, 5, 30, 10, 0));
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, "Гамбургер", 500F, LocalDateTime.of(2015, 5, 30, 10, 0));
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, "Кока Колы", 1000F, LocalDateTime.of(2015, 5, 30, 10, 0));
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, "Хот дог", 510F, LocalDateTime.of(2015, 5, 30, 10, 0));

    public static final List<Meal> MEALS = List.of(MEAL1, MEAL3, MEAL2);

    public static Meal getCreate(){
        return new Meal(null, "Созданное название", 1234F, LocalDateTime.of(2015, 5, 31, 10,0));
    }

    public static Meal getUpdate() {
        return new Meal(MEAL1_ID, "Новое название", 500F, MEAL1.getDateTime());
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }
}
