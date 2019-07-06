package ru.votingsystem;

import ru.votingsystem.model.Meal;

import java.time.LocalDateTime;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;

import static ru.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    private static final int MEAL1_ID = START_SEQ + 4;

    public final Meal MEAL1 = new Meal(MEAL1_ID, "Макароны", 500, LocalDateTime.of(2015, 5, 30, 10, 0));
    public final Meal MEAL2 = new Meal(MEAL1_ID + 1, "Шашлык", 1000, LocalDateTime.of(2015, 5, 30, 10, 0));
    public final Meal MEAL3 = new Meal(MEAL1_ID + 2, "Суп", 500, LocalDateTime.of(2015, 5, 30, 10, 0));
    public final Meal MEAL4 = new Meal(MEAL1_ID + 3, "Гамбургер", 500, LocalDateTime.of(2015, 5, 30, 10, 0));
    public final Meal MEAL5 = new Meal(MEAL1_ID + 4, "Кока Колы", 1000, LocalDateTime.of(2015, 5, 30, 10, 0));
    public final Meal MEAL6 = new Meal(MEAL1_ID + 5, "Хот дог", 510, LocalDateTime.of(2015, 5, 30, 10, 0));


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
