package ru.sberbank.jd.lesson10.service.stats;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson10.input.Cd;
import ru.sberbank.jd.lesson10.output.Registry;

class StatsServiceImplTest {

    private List<Cd> cds;
    private StatsServiceImpl statsService;

    @BeforeEach
    void setUp() {
        statsService = new StatsServiceImpl();
        cds = new ArrayList<>();

        Cd cd1 = new Cd();
        cd1.setTitle("Album 1");
        cd1.setArtist("Artist 1");
        cd1.setCountry("Country 1");
        cd1.setCompany("Company 1");
        cd1.setPrice(9.99);
        cd1.setYear(2000);
        cds.add(cd1);

        Cd cd2 = new Cd();
        cd2.setTitle("Album 2");
        cd2.setArtist("Artist 2");
        cd2.setCountry("Country 2");
        cd2.setCompany("Company 2");
        cd2.setPrice(12.99);
        cd2.setYear(2005);
        cds.add(cd2);

        Cd cd3 = new Cd();
        cd3.setTitle("Album 3");
        cd3.setArtist("Artist 3");
        cd3.setCountry("Country 3");
        cd3.setCompany("Company 3");
        cd3.setPrice(14.99);
        cd3.setYear(2010);
        cds.add(cd3);

        Cd cd4 = new Cd();
        cd4.setTitle("Album 4");
        cd4.setArtist("Artist 4");
        cd4.setCountry("Country 4");
        cd4.setCompany("Company 4");
        cd4.setPrice(11.99);
        cd4.setYear(2008);
        cds.add(cd4);

        Cd cd5 = new Cd();
        cd5.setTitle("Album 5");
        cd5.setArtist("Artist 5");
        cd5.setCountry("Country 5");
        cd5.setCompany("Company 5");
        cd5.setPrice(10.99);
        cd5.setYear(2003);
        cds.add(cd5);
    }

    /**
     * Тест формирования объекта Registry для дальнейшей сериализации.
     */
    @DisplayName("Тестирование формирования объекта Registry")
    @Test
    void getRegistryTest() {
        Registry registry = statsService.getRegistry(cds);
        Assertions.assertEquals(5, registry.getCountryCount());
    }
}