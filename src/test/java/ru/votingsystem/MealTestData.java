package ru.votingsystem;

import ru.votingsystem.model.Meal;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static ru.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int MEAL1_ID = START_SEQ + 4;

    public static final Meal MEAL1 = new Meal(MEAL1_ID, "Макароны", 500, LocalDate.of(2015, 5, 30));
    public static final Meal MEAL2 = new Meal(MEAL1_ID + 1, "Шашлык", 1000, LocalDate.of(2015, 5, 30));
    public static final Meal MEAL3 = new Meal(MEAL1_ID + 2, "Суп", 500, LocalDate.of(2015, 5, 30));
    public static final Meal MEAL4 = new Meal(MEAL1_ID + 3, "Гамбургер", 500, LocalDate.of(2015, 5, 30));
    public static final Meal MEAL5 = new Meal(MEAL1_ID + 4, "Кока Колы", 1000, LocalDate.of(2015, 5, 30));
    public static final Meal MEAL6 = new Meal(MEAL1_ID + 5, "Хот дог", 510, LocalDate.of(2015, 5, 30));

    public static final List<Meal> MEALS = List.of(MEAL1, MEAL3, MEAL2);

    public static Meal getCreate() {
        return new Meal(null, "Созданное название", 1234, LocalDate.of(2015, 5, 31));
    }

    public static Meal getUpdate() {
        return new Meal(MEAL1_ID, "Новое название", 500, MEAL1.getDate());
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
