package ru.sberbank.jd.lesson10.service.stats;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.sberbank.jd.lesson10.input.Cd;
import ru.sberbank.jd.lesson10.output.Album;
import ru.sberbank.jd.lesson10.output.Artist;
import ru.sberbank.jd.lesson10.output.Country;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Реализация интерфейса `StatsService` для получения статистических данных на основе списка CD.
 */
public class StatsServiceImpl implements StatsService {

    /**
     * Возвращает статистику альбомов на основе списка CD.
     *
     * @param cds список CD
     * @return Map, где ключом является название альбома, а значением - объект Album
     */
    @Override
    public Map<String, Album> getAlbumStats(List<Cd> cds) {
        return cds.stream().map(cd -> new Album(cd.getTitle(), cd.getYear()))
                .collect(Collectors.toMap(Album::getName, album -> album));
    }

    /**
     * Возвращает статистику артистов на основе списка CD и отображения альбомов.
     *
     * @param cds    список CD
     * @param albums Map альбомов
     * @return Map, где ключом является имя артиста, а значением - объект Artist
     */
    @Override
    public Map<String, Artist> getArtistStats(List<Cd> cds, Map<String, Album> albums) {
        return cds.stream()
                .map(cd -> new Artist(cd.getArtist()))
                .collect(Collectors.toMap(Artist::getName, artist -> artist, (existingArtist, newArtist) -> {
                            existingArtist.getAlbums().addAll(newArtist.getAlbums());
                            return existingArtist;
                        }
                ));
    }

    /**
     * Возвращает статистику стран на основе списка CD и отображения артистов.
     *
     * @param cds     список CD
     * @param artists Map артистов
     * @return Map, где ключом является название страны, а значением - объект Country
     */
    @Override
    public Map<String, Country> getCountryStats(List<Cd> cds, Map<String, Artist> artists) {
        Map<String, Country> countries = new HashMap<>();
        cds.stream()
                .map(cd -> new Country(cd.getCountry()))
                .distinct()
                .forEach(country -> cds.forEach(cd -> {
                    if (cd.getCountry().equals(country.getName())) {
                        country.getArtists().add(artists.get(cd.getArtist()));
                        countries.put(country.getName(), country);
                    }
                }));

        return countries;
    }

    /**
     * Возвращает объект Registry, содержащий статистические данные.
     *
     * @param cds       список CD
     * @param countries Map стран
     * @return объект Registry
     */
    @Override
    public Registry getRegistry(List<Cd> cds, Map<String, Country> countries) {
        return new Registry(countries.size(), new ArrayList<>(countries.values()));
    }
}
