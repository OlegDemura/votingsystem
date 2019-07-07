package ru.votingsystem;

import ru.votingsystem.model.Vote;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.votingsystem.model.AbstractBaseEntity.START_SEQ;

public class VoteTestData {
    public static final int VOTE1_ID = START_SEQ + 10;
    public static final int VOTE2_ID = START_SEQ + 11;
    public static final int VOTE3_ID = START_SEQ + 12;

    public static final Vote VOTE1 = new Vote(VOTE1_ID, LocalDateTime.of(2019, 7, 6, 13, 9));
    public static final Vote VOTE2 = new Vote(VOTE2_ID, LocalDateTime.of(2019, 7, 6, 13, 9));
    public static final Vote VOTE3 = new Vote(VOTE3_ID, LocalDateTime.of(2019, 7, 8, 13, 10));

    public static Vote getUpdate() {
        return new Vote(VOTE2.getId(), LocalDateTime.of(2019, 7, 6, 13, 20));
    }

    public static Vote getCreate() {
        return new Vote(null, LocalDateTime.of(2019, 7, 8, 14, 0));
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

}
