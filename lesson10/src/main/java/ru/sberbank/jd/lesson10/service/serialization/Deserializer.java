package ru.sberbank.jd.lesson10.service.serialization;

import java.util.List;
import ru.sberbank.jd.lesson10.input.Cd;

/**
 * Интерфейс десериализатор.
 */
public interface Deserializer {

    List<Cd> deserialize(String fileName);
}
