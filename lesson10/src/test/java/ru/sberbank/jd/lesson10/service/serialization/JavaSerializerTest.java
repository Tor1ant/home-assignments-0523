package ru.sberbank.jd.lesson10.service.serialization;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
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
 * Тестирование класса `JavaSerializer` для сериализации объектов с использованием Java Serialization.
 */
class JavaSerializerTest {
    private Registry registry;
    private final JavaSerializer javaSerializer = new JavaSerializer();

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
     * Тестирование сериализации с помощью Java Serialization.
     *
     * @throws IOException            если происходит ошибка ввода-вывода при работе с файлами
     * @throws ClassNotFoundException если класс не найден при десериализации
     */
    @DisplayName("Тестирование Сериализации с помощью java")
    @Test
    void serializeTest() throws IOException, ClassNotFoundException {
        String filePath = "src/test/resources/output/outputForTests/javaSerializerResult";
        String serialize = javaSerializer.serialize(registry, filePath);
        Registry registryAfterSerialize;
        Assertions.assertTrue(serialize.contains("java.io.ObjectOutputStream"));

        try (FileInputStream fileIn = new FileInputStream(filePath);
                ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
            registryAfterSerialize = (Registry) objectIn.readObject();
        }
        Assertions.assertEquals(registry, registryAfterSerialize);
    }

    /**
     * Тестирование неудачной сериализации с помощью Java Serialization.
     */
    @DisplayName("Тестирование неудачной Сериализации с помощью java")
    @Test
    void serializeFailTest() {
        JavaSerializer javaSerializer = new JavaSerializer();
        Assertions.assertThrows(RuntimeException.class,
                () -> javaSerializer.serialize(registry,
                        "/lesson10/src/test/resources/output/outputForTests/fail"));
    }
}