package ru.yandex.practicum.sleeptracker.common;

public enum Chronotype {
    OWL("Сова"),
    LARK("Жаворонок"),
    DOVE("Голубь");

    private final String nameRu;

    Chronotype(String nameRu) {
        this.nameRu = nameRu;
    }

    public String getNameRu() {
        return nameRu;
    }
}