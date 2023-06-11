package ru.sberbank.jd.lesson10.service.stats;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson10.input.Cd;
import ru.sberbank.jd.lesson10.output.Album;
import ru.sberbank.jd.lesson10.output.Artist;
import ru.sberbank.jd.lesson10.output.Country;
import ru.sberbank.jd.lesson10.output.Registry;

class StatsServiceImplTest {

    private List<Cd> cds;
    private StatsServiceImpl statsService;
    private Cd cd1;
    private Cd cd2;
    private Cd cd3;
    private Cd cd4;
    private Cd cd5;

    @BeforeEach
    void setUp() {
        statsService = new StatsServiceImpl();
        cds = new ArrayList<>();

        cd1 = new Cd();
        cd1.setTitle("Album 1");
        cd1.setArtist("Artist 1");
        cd1.setCountry("Country 1");
        cd1.setCompany("Company 1");
        cd1.setPrice(9.99);
        cd1.setYear(2000);
        cds.add(cd1);

        cd2 = new Cd();
        cd2.setTitle("Album 2");
        cd2.setArtist("Artist 2");
        cd2.setCountry("Country 2");
        cd2.setCompany("Company 2");
        cd2.setPrice(12.99);
        cd2.setYear(2005);
        cds.add(cd2);

        cd3 = new Cd();
        cd3.setTitle("Album 3");
        cd3.setArtist("Artist 3");
        cd3.setCountry("Country 3");
        cd3.setCompany("Company 3");
        cd3.setPrice(14.99);
        cd3.setYear(2010);
        cds.add(cd3);

        cd4 = new Cd();
        cd4.setTitle("Album 4");
        cd4.setArtist("Artist 4");
        cd4.setCountry("Country 4");
        cd4.setCompany("Company 4");
        cd4.setPrice(11.99);
        cd4.setYear(2008);
        cds.add(cd4);

        cd5 = new Cd();
        cd5.setTitle("Album 5");
        cd5.setArtist("Artist 5");
        cd5.setCountry("Country 5");
        cd5.setCompany("Company 5");
        cd5.setPrice(10.99);
        cd5.setYear(2003);
        cds.add(cd5);
    }

    @DisplayName("Тестирование получения альбомов из списка CD")
    @Test
    void getAlbumStatsTest() {
        Map<String, Album> albumStats = statsService.getAlbumStats(cds);
        Assertions.assertEquals(5, albumStats.size());
        Assertions.assertEquals(cd4.getYear(), albumStats.get(cd4.getTitle()).getYear());
        Assertions.assertEquals(cd2.getTitle(), albumStats.get(cd2.getTitle()).getName());
    }

    @DisplayName("Тестирование получения артистов из списка CD")
    @Test
    void getArtistStatsTest() {
        Map<String, Album> albumStats = statsService.getAlbumStats(cds);
        Map<String, Artist> artistStats = statsService.getArtistStats(cds, albumStats);
        Assertions.assertEquals(5, artistStats.size());
        Assertions.assertEquals(cd4.getArtist(), artistStats.get(cd4.getArtist()).getName());
        Assertions.assertEquals(cd1.getArtist(), artistStats.get(cd1.getArtist()).getName());
    }

    @DisplayName("Тестирование получения альбомов из списка CD")
    @Test
    void getCountryStatsTest() {
        Map<String, Album> albumStats = statsService.getAlbumStats(cds);
        Map<String, Artist> artistStats = statsService.getArtistStats(cds, albumStats);
        Map<String, Country> countryStats = statsService.getCountryStats(cds, artistStats);
        Assertions.assertEquals(5, countryStats.size());
        Assertions.assertEquals(cd4.getCountry(), countryStats.get(cd4.getCountry()).getName());
        Assertions.assertEquals(cd5.getCountry(), countryStats.get(cd5.getCountry()).getName());
    }

    @DisplayName("Тестирование получения альбомов из списка CD")
    @Test
    void getRegistryTest() {
        Map<String, Album> albumStats = statsService.getAlbumStats(cds);
        Map<String, Artist> artistStats = statsService.getArtistStats(cds, albumStats);
        Map<String, Country> countryStats = statsService.getCountryStats(cds, artistStats);
        Registry registry = statsService.getRegistry(cds, countryStats);
        Assertions.assertEquals(5, registry.getCountryCount());
    }
}