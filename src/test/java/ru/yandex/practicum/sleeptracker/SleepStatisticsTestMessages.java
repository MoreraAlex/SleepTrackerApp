package ru.yandex.practicum.sleeptracker;

public class SleepStatisticsTestMessages {
    static final String EMPTY_TOTAL_SESSIONS =
            "Для пустого списка сессий количество должно быть 0";
    static final String SAMPLE_TOTAL_SESSIONS =
            "Количество сессий в sample должно быть равно 4";

    static final String EMPTY_MIN_DURATION =
            "Минимальная длительность для пустого списка должна быть 0";
    static final String SAMPLE_MIN_DURATION =
            "Минимальная длительность сна в sample должна быть 50 минут";

    static final String EMPTY_MAX_DURATION =
            "Максимальная длительность для пустого списка должна быть 0";
    static final String SAMPLE_MAX_DURATION =
            "Максимальная длительность сна в sample должна быть 585 минут";

    static final String EMPTY_AVERAGE_DURATION =
            "Средняя длительность для пустого списка должна быть 0";
    static final String SAMPLE_AVERAGE_DURATION =
            "Средняя длительность сна должна совпадать с вычисленной вручную";

    static final String EMPTY_BAD_QUALITY =
            "Для пустого списка количество плохих сессий должно быть 0";
    static final String SAMPLE_BAD_QUALITY =
            "В sample должна быть ровно одна сессия с плохим качеством";

    static final String EMPTY_SLEEPLESS_NIGHTS =
            "Для пустого списка бессонных ночей быть не должно";
    static final String NO_SLEEPLESS_NIGHTS =
            "В sample не должно быть бессонных ночей";
    static final String ONE_SLEEPLESS_NIGHT =
            "Между 01.10 и 03.10 должна быть ровно одна бессонная ночь";
    static final String DAY_SLEEP_COUNTS_AS_SLEEPLESS =
            "Дневной сон без ночного должен считаться бессонной ночью";
    static final String OVERLAPPING_NIGHT_SESSIONS =
            "Перекрывающиеся ночные сессии не должны создавать бессонную ночь";

    static final String CHRONOTYPE_OWL =
            "Позднее засыпание и поздний подъём должны определить хронотип «Сова»";
    static final String CHRONOTYPE_LARK =
            "Раннее засыпание и ранний подъём должны определить хронотип «Жаворонок»";
    static final String CHRONOTYPE_DOVE_MAJORITY =
            "При большинстве нейтральных сессий хронотип должен быть «Голубь»";
    static final String CHRONOTYPE_DOVE_ON_TIE =
            "При равенстве голосов хронотип должен по умолчанию быть «Голубь»";

}
