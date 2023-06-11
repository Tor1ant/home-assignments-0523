package ru.sberbank.jd.lesson10.service.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import ru.sberbank.jd.lesson10.exceptions.FileNotFoundException;
import ru.sberbank.jd.lesson10.input.Catalog;
import ru.sberbank.jd.lesson10.input.Cd;

/**
 * Класс, реализующий десериализацию объектов из формата XML с использованием библиотеки Jackson.
 */
public class XmlDeserializer implements Deserializer {
    private final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Десериализует содержимое XML-файла в список объектов типа `Cd`.
     *
     * @param filePath путь к XML-файлу, который необходимо десериализовать
     * @return список объектов типа `Cd`, полученных из десериализации
     * @throws FileNotFoundException если указанный файл не найден
     */
    public List<Cd> deserialize(String filePath) {
        List<Cd> result;
        try {
            String readContent = new String(Files.readAllBytes(Paths.get(filePath)));

            Catalog cdList = xmlMapper.readValue(readContent, Catalog.class);

            result = cdList.getCds();
        } catch (IOException e) {
            throw new FileNotFoundException("Файл для получения данных не найден.");
        }
        return result;
    }
}
