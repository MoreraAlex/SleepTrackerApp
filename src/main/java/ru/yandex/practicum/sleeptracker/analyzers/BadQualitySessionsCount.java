package ru.yandex.practicum.sleeptracker.analyzers;

import ru.yandex.practicum.sleeptracker.common.Quality;
import ru.yandex.practicum.sleeptracker.records.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.common.SleepAnalyzer;
import ru.yandex.practicum.sleeptracker.records.SleepingSession;

import java.util.List;

public class BadQualitySessionsCount implements SleepAnalyzer {
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        long count = sessions.stream()
                .filter(s -> s.quality() == Quality.BAD)
                .count();
        return new SleepAnalysisResult("Количество сессий с плохим качеством сна", count);
    }
}