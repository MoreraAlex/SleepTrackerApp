package ru.yandex.practicum.sleeptracker.analyzers;

import ru.yandex.practicum.sleeptracker.records.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.common.SleepAnalyzer;
import ru.yandex.practicum.sleeptracker.records.SleepingSession;

import java.util.List;

public class TotalSleepSessions implements SleepAnalyzer {
    public SleepAnalysisResult apply(List<SleepingSession> sessions) {
        return new SleepAnalysisResult("Общее количество сессий сна", sessions.size());
    }
}