package ru.sberbank.jd.lesson10.service.serialization;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson10.output.Album;
import ru.sberbank.jd.lesson10.output.Artist;
import ru.sberbank.jd.lesson10.output.Country;
import ru.sberbank.jd.lesson10.output.Registry;

/**
 * Тестирование класса `XmlSerializer` для сериализации объектов в XML.
 */
class XmlSerializerTest {

    private Registry registry;
    private final XmlSerializer xmlSerializer = new XmlSerializer();
    private final XmlMapper xmlMapper = new XmlMapper();

    /**
     * Подготовка данных перед каждым тестовым методом.
     * Создает объекты Country, Artist и Album, инициализирует их значениями, и затем создает объект Registry
     * с одной страной, содержащей одного артиста с одним альбомом.
     */
    @BeforeEach
    void setUp() {
        Country country = new Country("Russia");
        Artist artist = new Artist("Чайковсий Пётр Ильич");
        Album album = new Album("Времена года", 1876);
        artist.setAlbums(List.of(album));
        country.setArtists(List.of(artist));
        registry = new Registry(1, List.of(country));
    }

    /**
     * Тест сериализации в XML и сравнение с ожидаемым результатом.
     * Проверяет, что сериализованный XML соответствует ожидаемому объекту Registry.
     */
    @DisplayName("Тест сериализации в XML и сравнение с ожидаемым результатом")
    @Test
    void serializeAndCompareTest() throws IOException {
        String filename = "src/test/resources/output/outputForTests/xmlSerializerResult.xml";
        xmlSerializer.serialize(registry, filename);
        String readContent = new String(Files.readAllBytes(Paths.get(filename)));
        Registry registryAfterSerial = xmlMapper.readValue(readContent, Registry.class);
        Assertions.assertEquals(registry, registryAfterSerial);
    }

    /**
     * Тест сериализации с выбросом исключения.
     * Проверяет, что при сериализации с некорректным путем файла выбрасывается исключение RuntimeException.
     */
    @DisplayName("Тест сериализации с выбросом исключения")
    @Test
    void serializeExceptionTest() {
        Registry registry = new Registry();
        String filename = "nonexistent/file.xml";

        Assertions.assertThrows(RuntimeException.class, () -> xmlSerializer.serialize(registry, filename));
    }
}
