package ru.votingsystem;

import ru.votingsystem.model.Vote;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.votingsystem.RestaurantTestData.RESTAURANT1;
import static ru.votingsystem.RestaurantTestData.RESTAURANT2;
import static ru.votingsystem.UserTestData.ADMIN;
import static ru.votingsystem.UserTestData.USER;
import static ru.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 10;
    public static final int VOTE_NOT_FOUND = 1;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDate.of(2019, 7, 6), USER, RESTAURANT2);
    public static final Vote VOTE2 = new Vote(VOTE1_ID+11, LocalDate.of(2019, 7, 6), ADMIN, RESTAURANT1);
    public static final Vote VOTE3 = new Vote(VOTE1_ID+12, LocalDate.of(2019, 7, 8), ADMIN, RESTAURANT2);
    public static final Vote VOTE4 = new Vote(VOTE1_ID+13, LocalDate.of(2019, 7, 9), ADMIN, RESTAURANT1);
    public static final Vote VOTE5 = new Vote(VOTE1_ID+14, LocalDate.of(2019, 7, 10), ADMIN, RESTAURANT1);
    public static final Vote VOTE6 = new Vote(VOTE1_ID+15, LocalDate.of(2019, 7, 11), ADMIN, RESTAURANT1);

    public static final Integer RESTAURANT1_COUNT = 4;

    public static Vote getCreate() {
        return new Vote(null, LocalDate.now(), ADMIN, RESTAURANT1);
    }

    public static void assertMatch(Vote actual, Vote expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant", "user");
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
