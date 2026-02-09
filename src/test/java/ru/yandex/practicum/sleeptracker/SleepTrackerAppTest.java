package ru.yandex.practicum.sleeptracker;

import org.junit.jupiter.api.Test;
import ru.yandex.practicum.sleeptracker.analyzers.*;
import ru.yandex.practicum.sleeptracker.common.Quality;
import ru.yandex.practicum.sleeptracker.records.SleepingSession;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.yandex.practicum.sleeptracker.SleepStatisticsTestMessages.*;

class SleepTrackerAppTest {

    private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("dd.MM.yy HH:mm");

    private static LocalDateTime dt(String s) {
        return LocalDateTime.parse(s, DTF);
    }

    private final List<SleepingSession> empty = List.of();

    private final List<SleepingSession> sample = List.of(
            new SleepingSession(dt("01.10.25 22:15"), dt("02.10.25 08:00"), Quality.GOOD),
            new SleepingSession(dt("02.10.25 23:00"), dt("03.10.25 08:00"), Quality.NORMAL),
            new SleepingSession(dt("03.10.25 14:30"), dt("03.10.25 15:20"), Quality.NORMAL),
            new SleepingSession(dt("03.10.25 23:30"), dt("04.10.25 06:20"), Quality.BAD)
    );

    @Test
    void totalSessions() {
        assertEquals(0,
                new TotalSleepSessions().apply(empty).value(),
                EMPTY_TOTAL_SESSIONS);

        assertEquals(4,
                new TotalSleepSessions().apply(sample).value(),
                SAMPLE_TOTAL_SESSIONS);
    }

    @Test
    void minDuration() {
        assertEquals(0L,
                new MinSleepDuration().apply(empty).value(),
                EMPTY_MIN_DURATION);

        assertEquals(50L,
                new MinSleepDuration().apply(sample).value(),
                SAMPLE_MIN_DURATION);
    }

    @Test
    void maxDuration() {
        assertEquals(0L,
                new MaxSleepDuration().apply(empty).value(),
                EMPTY_MAX_DURATION);

        assertEquals(585L,
                new MaxSleepDuration().apply(sample).value(),
                SAMPLE_MAX_DURATION);
    }

    @Test
    void averageDuration() {
        assertEquals(0L,
                new AverageSleepDuration().apply(empty).value(),
                EMPTY_AVERAGE_DURATION);

        long avg = Math.round(sample.stream()
                .mapToLong(s -> java.time.Duration
                        .between(s.start(), s.end())
                        .toMinutes())
                .average()
                .getAsDouble());

        assertEquals(avg,
                new AverageSleepDuration().apply(sample).value(),
                SAMPLE_AVERAGE_DURATION);
    }

    @Test
    void badQuality() {
        assertEquals(0L,
                new BadQualitySessionsCount().apply(empty).value(),
                EMPTY_BAD_QUALITY);

        assertEquals(1L,
                new BadQualitySessionsCount().apply(sample).value(),
                SAMPLE_BAD_QUALITY);
    }

    @Test
    void sleeplessNights_empty() {
        assertEquals(0L,
                new SleeplessNightsCount().apply(empty).value(),
                EMPTY_SLEEPLESS_NIGHTS);
    }

    @Test
    void sleeplessNights_noSleepless() {
        assertEquals(0L,
                new SleeplessNightsCount().apply(sample).value(),
                NO_SLEEPLESS_NIGHTS);
    }

    @Test
    void sleeplessNights_oneMissing() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 01:00"), dt("01.10.25 07:00"), Quality.GOOD),
                new SleepingSession(dt("03.10.25 01:00"), dt("03.10.25 07:00"), Quality.GOOD)
        );

        assertEquals(1L,
                new SleeplessNightsCount().apply(sessions).value(),
                ONE_SLEEPLESS_NIGHT);
    }

    @Test
    void sleeplessNights_dayOnly_firstAfterNoon() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 14:00"), dt("01.10.25 16:00"), Quality.NORMAL)
        );

        assertEquals(1L,
                new SleeplessNightsCount().apply(sessions).value(),
                DAY_SLEEP_COUNTS_AS_SLEEPLESS);
    }

    @Test
    void sleeplessNights_lateWakeEarlyBed() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 23:00"), dt("02.10.25 05:00"), Quality.NORMAL),
                new SleepingSession(dt("02.10.25 02:00"), dt("02.10.25 08:00"), Quality.NORMAL)
        );

        assertEquals(0L,
                new SleeplessNightsCount().apply(sessions).value(),
                OVERLAPPING_NIGHT_SESSIONS);
    }

    @Test
    void chronotype_owl() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 23:30"), dt("02.10.25 10:00"), Quality.GOOD),
                new SleepingSession(dt("02.10.25 00:10"), dt("02.10.25 09:30"), Quality.GOOD)
        );

        assertEquals("Сова",
                new UserChronotype().apply(sessions).value(),
                CHRONOTYPE_OWL);
    }

    @Test
    void chronotype_lark() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 21:00"), dt("02.10.25 06:30"), Quality.GOOD),
                new SleepingSession(dt("02.10.25 20:45"), dt("02.10.25 06:00"), Quality.GOOD)
        );

        assertEquals("Жаворонок",
                new UserChronotype().apply(sessions).value(),
                CHRONOTYPE_LARK);
    }

    @Test
    void chronotype_dove_majority() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 22:30"), dt("02.10.25 07:30"), Quality.GOOD),
                new SleepingSession(dt("02.10.25 23:10"), dt("03.10.25 08:10"), Quality.GOOD),
                new SleepingSession(dt("03.10.25 22:00"), dt("04.10.25 07:00"), Quality.GOOD)
        );

        assertEquals("Голубь",
                new UserChronotype().apply(sessions).value(),
                CHRONOTYPE_DOVE_MAJORITY);
    }

    @Test
    void chronotype_tie_fallsToDove() {
        var sessions = List.of(
                new SleepingSession(dt("01.10.25 23:30"), dt("02.10.25 10:00"), Quality.GOOD),
                new SleepingSession(dt("02.10.25 21:00"), dt("03.10.25 06:00"), Quality.GOOD)
        );

        assertEquals("Голубь",
                new UserChronotype().apply(sessions).value(),
                CHRONOTYPE_DOVE_ON_TIE);
    }
}