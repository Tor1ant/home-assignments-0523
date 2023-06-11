package ru.sberbank.jd.lesson10.service.serialization;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * {@code JavaSerializer} представляет сериализатор, который использует стандартную сериализацию Java для сериализации
 * объектов в файлы.
 */
public class JavaSerializer implements Serializer {

    /**
     * Сериализует объект реестра в файл с использованием стандартной сериализации Java.
     *
     * @param registry объект реестра для сериализации
     * @param filePath путь к файлу, в который будет произведена сериализация
     * @return строковое представление сериализованного объекта
     * @throws RuntimeException если произошла ошибка ввода-вывода во время сериализации
     */
    @Override
    public String serialize(Registry registry, String filePath) {
        String result;
        try (FileOutputStream fileOut = new FileOutputStream(filePath);
                ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(registry);
            result = objectOut.toString();
            System.out.println("Объект " + objectOut + " успешно сериализован в файл " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return result;
    }
}
