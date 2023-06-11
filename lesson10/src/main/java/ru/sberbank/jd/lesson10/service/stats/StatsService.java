package ru.sberbank.jd.lesson10.service.stats;

import java.util.List;
import ru.sberbank.jd.lesson10.input.Cd;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Интерфейс счётчика статистики.
 */
public interface StatsService {
    Registry getRegistry(List<Cd> cds);
}
