package ru.sberbank.jd.lesson10.service.serialization;

import com.fasterxml.jackson.databind.ObjectMapper;
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
 * Тестирование класса `JsonSerializer` для сериализации объектов в JSON формат.
 */
class JsonSerializerTest {

    private Registry registry;
    private final JsonSerializer jsonSerializer = new JsonSerializer();
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Подготовка перед каждым тестом.
     * Создает тестовые данные - объект Registry.
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
     * Тестирование сериализации в JSON.
     *
     * @throws IOException если происходит ошибка ввода-вывода при работе с файлами
     */
    @DisplayName("Тестирование Сериализации в json")
    @Test
    void serializeTest() throws IOException {
        jsonSerializer.serialize(registry,
                "src/test/resources/output/outputForTests/jsonSerializerResult.json");
        String jsonString = Files.readString(Paths
                .get("src/test/resources/output/outputForTests/jsonSerializerResult.json"));

        Registry registryAfterSerial = objectMapper.readValue(jsonString, Registry.class);
        Assertions.assertEquals(registry, registryAfterSerial);
    }

    /**
     * Тестирование сериализации с выбросом исключения.
     */
    @DisplayName("Тест сериализации с выбросом исключения")
    @Test
    void serializeExceptionTest() {
        Registry registry = new Registry();
        String filename = "nonexistent/file.json";

        Assertions.assertThrows(RuntimeException.class, () -> jsonSerializer.serialize(registry, filename));
    }
}