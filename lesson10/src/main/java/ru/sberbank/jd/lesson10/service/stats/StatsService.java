package ru.sberbank.jd.lesson10.service.stats;

import java.util.List;
import java.util.Map;
import ru.sberbank.jd.lesson10.input.Cd;
import ru.sberbank.jd.lesson10.output.Album;
import ru.sberbank.jd.lesson10.output.Artist;
import ru.sberbank.jd.lesson10.output.Country;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Интерфейс счётчика статистики.
 */
public interface StatsService {

    Map<String, Album> getAlbumStats(List<Cd> cds);

    Map<String, Artist> getArtistStats(List<Cd> cds, Map<String, Album> albums);

    Map<String, Country> getCountryStats(List<Cd> cds, Map<String, Artist> artists);

    Registry getRegistry(List<Cd> cds, Map<String, Country> countries);
}
