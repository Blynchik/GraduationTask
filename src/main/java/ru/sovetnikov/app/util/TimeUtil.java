package ru.sovetnikov.app.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@UtilityClass
public class TimeUtil {

    private static final LocalDateTime END_VOTING = LocalDateTime.of(LocalDate.now(), LocalTime.of(11, 0));

    public static <T extends Comparable<T>> boolean checkTime(LocalDateTime voteDateTime) {
        return (voteDateTime.compareTo(END_VOTING.minusDays(1)) >= 0) && (voteDateTime.compareTo(END_VOTING) < 0) && (END_VOTING.compareTo(LocalDateTime.now()) > 0);
    }
}
