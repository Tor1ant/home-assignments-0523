package ru.sberbank.jd.lesson10.service.serialization;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson10.exceptions.FileNotFoundException;
import ru.sberbank.jd.lesson10.input.Cd;

/**
 * Тестирование класса `XmlDeserializer` для десериализации XML в объекты POJO.
 */
class XmlDeserializerTest {

    /**
     * Тестирование десериализации XML в POJO.
     * Проверяет корректность десериализации и соответствие ожидаемого объекта Cd.
     */
    @DisplayName("Тестирование десериализации XML в POJO")
    @Test
    void deserializeTest() {
        Cd cd = new Cd();
        cd.setArtist("Bob Dylan");
        cd.setTitle("Empire Burlesque");
        cd.setCompany("Columbia");
        cd.setPrice(10.9);
        cd.setYear(1985);
        cd.setCountry("USA");
        XmlDeserializer xmlDeserializer = new XmlDeserializer();
        List<Cd> cds = xmlDeserializer.deserialize("src/test/resources/input/cd_catalog.xml");
        Assertions.assertEquals(26, cds.size());
        Assertions.assertEquals(cd, cds.get(0));
    }

    /**
     * Тестирование десериализации XML в POJO из несуществующего файла.
     * Проверяет, что при попытке десериализации из несуществующего файла выбрасывается исключение
     * FileNotFoundException.
     */
    @DisplayName("Тестирование десериализации XML в POJO из несуществующего файла")
    @Test
    void deserializeFailTest() {
        XmlDeserializer xmlDeserializer = new XmlDeserializer();
        Assertions.assertThrows(FileNotFoundException.class,
                () -> xmlDeserializer.deserialize("src/test/resources/input/cd_catalogTest.xml"));
    }
}