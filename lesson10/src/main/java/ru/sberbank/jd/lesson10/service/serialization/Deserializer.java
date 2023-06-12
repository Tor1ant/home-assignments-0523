package ru.sberbank.jd.lesson10.service.serialization;

import ru.sberbank.jd.lesson10.input.Catalog;

/**
 * Интерфейс десериализатор.
 */
public interface Deserializer {

    Catalog deserialize(String fileName);
}
