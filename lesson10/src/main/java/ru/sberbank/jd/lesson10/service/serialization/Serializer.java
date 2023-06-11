package ru.sberbank.jd.lesson10.service.serialization;

import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Интерфейс Сериализатор.
 */
public interface Serializer {

    boolean serialize(Registry registry, String filename);
}
