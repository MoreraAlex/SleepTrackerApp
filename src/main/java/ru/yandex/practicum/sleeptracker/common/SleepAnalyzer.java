package ru.yandex.practicum.sleeptracker.common;

import ru.yandex.practicum.sleeptracker.records.SleepAnalysisResult;
import ru.yandex.practicum.sleeptracker.records.SleepingSession;

import java.util.List;
import java.util.function.Function;

@FunctionalInterface
public interface SleepAnalyzer extends Function<List<SleepingSession>, SleepAnalysisResult> {}