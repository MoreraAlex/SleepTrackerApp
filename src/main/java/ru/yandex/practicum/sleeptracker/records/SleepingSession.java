package ru.yandex.practicum.sleeptracker.records;

import ru.yandex.practicum.sleeptracker.common.Quality;

import java.time.LocalDateTime;

public record SleepingSession(LocalDateTime start, LocalDateTime end, Quality quality) {}