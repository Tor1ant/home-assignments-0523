package ru.sberbank.jd.lesson10.service.serialization;

import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Интерфейс Сериализатор.
 */
public interface Serializer {

    String serialize(Registry registry, String filename);
}
