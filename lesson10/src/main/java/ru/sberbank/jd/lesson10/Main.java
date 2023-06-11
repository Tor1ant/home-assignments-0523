package ru.sberbank.jd.lesson10;

import ru.sberbank.jd.lesson10.input.Catalog;
import ru.sberbank.jd.lesson10.output.Registry;
import ru.sberbank.jd.lesson10.service.serialization.JavaSerializer;
import ru.sberbank.jd.lesson10.service.serialization.JsonSerializer;
import ru.sberbank.jd.lesson10.service.serialization.Serializer;
import ru.sberbank.jd.lesson10.service.serialization.XmlDeserializer;
import ru.sberbank.jd.lesson10.service.serialization.XmlSerializer;
import ru.sberbank.jd.lesson10.service.stats.StatsService;
import ru.sberbank.jd.lesson10.service.stats.StatsServiceImpl;

/**
 * Главный класс приложения, отвечающий за выполнение сериализации и статистических вычислений по данным о CD. Загружает
 * данные из XML-файла, выполняет вычисление статистики по альбомам, артистам и странам, а затем производит сериализацию
 * результатов в различные форматы.
 */
public class Main {

    private static final String PATH_TO_OUTPUT_FILE = "lesson10/src/main/resources/output/";

    /**
     * Метод main является точкой входа в приложение. Загружает данные из XML-файла, вычисляет статистику и выполняет
     * сериализацию результатов.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        XmlDeserializer xmlDeserializer = new XmlDeserializer();
        StatsService statsService = new StatsServiceImpl();
        Serializer javaSerializer = new JavaSerializer();
        Serializer jsonSerializer = new JsonSerializer();
        Serializer xmlSerializer = new XmlSerializer();
        Catalog catalog = xmlDeserializer.deserialize("lesson10/src/main/resources/input/cd_catalog.xml");
        Registry registry = statsService.getRegistry(catalog.getCds());
        javaSerializer.serialize(registry, PATH_TO_OUTPUT_FILE + "artist_by_country.serialized");
        jsonSerializer.serialize(registry, PATH_TO_OUTPUT_FILE + "artist_by_country.json");
        xmlSerializer.serialize(registry, PATH_TO_OUTPUT_FILE + "artist_by_country.xml");
    }
}
