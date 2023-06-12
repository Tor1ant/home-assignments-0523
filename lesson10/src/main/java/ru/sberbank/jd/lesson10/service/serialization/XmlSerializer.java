package ru.sberbank.jd.lesson10.service.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Класс, реализующий сериализацию объектов в формат XML с использованием библиотеки Jackson.
 */
public class XmlSerializer implements Serializer {
    private final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Сериализует объект типа `Registry` в формат XML и записывает его в файл.
     *
     * @param registry объект типа `Registry`, который необходимо сериализовать
     * @param filename имя файла, в который будет записан XML-контент
     * @return true, если сериализация прошла успешно
     * @throws RuntimeException в случае ошибки при сериализации или записи в файл
     */
    @Override
    public boolean serialize(Registry registry, String filename) {
        String xmlString;
        try {
            xmlString = xmlMapper.writeValueAsString(registry);
            File xmlOutput = new File(filename);
            FileWriter fileWriter = new FileWriter(xmlOutput);
            fileWriter.write(xmlString);
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return true;
    }
}
