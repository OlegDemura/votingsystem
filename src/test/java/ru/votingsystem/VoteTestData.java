package ru.votingsystem;

import ru.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1;
import static ru.votingsystem.UserTestData.USER;

public class VoteTestData {

    public static final Integer RESTAURANT1_COUNT = 4;

    public static Vote getCreate() {
        return new Vote(null, LocalDate.now(), USER, RESTAURANT1);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected);
    }

    public static void assertMatch(Iterable<Vote> actual, Vote... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Vote> actual, Iterable<Vote> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant", "user").isEqualTo(expected);
    }

    public static void assertMatch(Integer actual, Integer expected){
        assertThat(actual).isEqualTo(expected);
    }
}
