package ru.sberbank.jd.lesson10.service.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import ru.sberbank.jd.lesson10.exceptions.FileNotFoundException;
import ru.sberbank.jd.lesson10.input.Catalog;

/**
 * Класс, реализующий десериализацию объектов из формата XML с использованием библиотеки Jackson.
 */
public class XmlDeserializer implements Deserializer {

    private final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Десериализует содержимое XML-файла в список объектов типа `Cd`.
     *
     * @param filePath путь к XML-файлу, который необходимо десериализовать
     * @return Объект Catalog, который содержит список объектов типа `Cd`, полученных из десериализации
     * @throws FileNotFoundException если указанный файл не найден
     */
    public Catalog deserialize(String filePath) {
        Catalog catalog;
        try {
            String readContent = new String(Files.readAllBytes(Paths.get(filePath)));

            catalog = xmlMapper.readValue(readContent, Catalog.class);

        } catch (IOException e) {
            throw new FileNotFoundException("Файл для получения данных не найден.");
        }
        return catalog;
    }
}
