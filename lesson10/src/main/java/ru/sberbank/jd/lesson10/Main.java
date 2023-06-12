package ru.sberbank.jd.lesson10;

import java.util.List;
import java.util.Map;
import ru.sberbank.jd.lesson10.input.Cd;
import ru.sberbank.jd.lesson10.output.Album;
import ru.sberbank.jd.lesson10.output.Artist;
import ru.sberbank.jd.lesson10.output.Country;
import ru.sberbank.jd.lesson10.output.Registry;
import ru.sberbank.jd.lesson10.service.serialization.JavaSerializer;
import ru.sberbank.jd.lesson10.service.serialization.JsonSerializer;
import ru.sberbank.jd.lesson10.service.serialization.Serializer;
import ru.sberbank.jd.lesson10.service.serialization.XmlDeserializer;
import ru.sberbank.jd.lesson10.service.serialization.XmlSerializer;
import ru.sberbank.jd.lesson10.service.stats.StatsService;
import ru.sberbank.jd.lesson10.service.stats.StatsServiceImpl;

/**
 * Главный класс приложения, отвечающий за выполнение сериализации и статистических вычислений по данным о CD.
 * Загружает данные из XML-файла, выполняет вычисление статистики по альбомам, артистам и странам,
 * а затем производит сериализацию результатов в различные форматы.
 */
public class Main {

    /**
     * Метод main является точкой входа в приложение.
     * Загружает данные из XML-файла, вычисляет статистику и выполняет сериализацию результатов.
     *
     * @param args Аргументы командной строки (не используются).
     */
    public static void main(String[] args) {
        XmlDeserializer xmlDeserializer = new XmlDeserializer();
        StatsService statsService = new StatsServiceImpl();
        Serializer javaSerializer = new JavaSerializer();
        Serializer jsonSerializer = new JsonSerializer();
        Serializer xmlSerializer = new XmlSerializer();
        List<Cd> cds = xmlDeserializer.deserialize("lesson10/src/main/resources/input/cd_catalog.xml");
        Map<String, Album> albumStats = statsService.getAlbumStats(cds);
        Map<String, Artist> artistStats = statsService.getArtistStats(cds, albumStats);
        Map<String, Country> countryStats = statsService.getCountryStats(cds, artistStats);
        Registry registry = statsService.getRegistry(cds, countryStats);
        javaSerializer.serialize(registry, "lesson10/src/main/resources/output/artist_by_country.serialized");
        jsonSerializer.serialize(registry, "lesson10/src/main/resources/output/artist_by_country.json");
        xmlSerializer.serialize(registry, "lesson10/src/main/resources/output/artist_by_country.xml");
    }
}
