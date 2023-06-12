package ru.sberbank.jd.lesson10.service.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Класс, реализующий сериализацию объектов в формат JSON с использованием библиотеки Jackson.
 */
public class JsonSerializer implements Serializer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Сериализует объект `Registry` в формат JSON и сохраняет его в указанный файл.
     *
     * @param registry объект `Registry` для сериализации
     * @param filename путь к файлу, в который будет сохранен результат сериализации
     * @return true, если сериализация прошла успешно
     * @throws RuntimeException если возникла ошибка во время сериализации
     */
    @Override
    public boolean serialize(Registry registry, String filename) {
        try {
            objectMapper.writeValue(new File(filename), registry);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
