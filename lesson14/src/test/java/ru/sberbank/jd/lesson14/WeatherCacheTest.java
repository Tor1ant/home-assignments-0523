package ru.sberbank.jd.lesson14;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * Класс, тестирующий функциональность приложения.
 */
class WeatherCacheTest {

    @InjectMocks
    private WeatherCache weatherCache;
    @Mock
    private WeatherProvider weatherProvider;
    private WeatherInfo weatherInfo;

    /**
     * Подготовка окружения перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        weatherCache = new WeatherCache(weatherProvider);
        weatherInfo = new WeatherInfo();
        weatherInfo.setCity("Moscow");
        weatherInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        weatherInfo.setTemperature(25.5);
        weatherInfo.setWindSpeed(17.5);
        weatherInfo.setDescription("Тестовое описание");
        weatherInfo.setPressure(1000);
        weatherInfo.setShortDescription("тестовое короткое описание");
    }

    /**
     * Тестовый сценарий для получения информации о погоде, когда она отсутствует в кэше.
     */
    @Test
    void getWeatherInfo_WeatherInfoNotInCache_ReturnsWeatherInfo() {
        when(weatherProvider.get(anyString())).thenReturn(weatherInfo);
        WeatherInfo moscow = weatherCache.getWeatherInfo("Moscow");
        assertEquals(weatherInfo, moscow);
        verify(weatherProvider, times(1)).get(anyString());
    }

    /**
     * Тестовый сценарий для получения информации о погоде, когда она присутствует в кэше.
     *
     * @throws NoSuchFieldException   если поле не найдено
     * @throws IllegalAccessException если не удалось получить доступ к полю
     */
    @Test
    void getWeatherInfo_WeatherInfoInCache_ReturnsWeatherInfo() throws NoSuchFieldException, IllegalAccessException {

        String city = "London";
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCity(city);
        weatherInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        weatherInfo.setTemperature(25.5);
        weatherInfo.setWindSpeed(17.5);
        weatherInfo.setDescription("Тестовое описание");
        weatherInfo.setPressure(1000);
        weatherInfo.setShortDescription("тестовое короткое описание");

        Map<String, WeatherInfo> cache = new HashMap<>();
        cache.put(city, weatherInfo);

        java.lang.reflect.Field cacheField = WeatherCache.class.getDeclaredField("cache");
        cacheField.setAccessible(true);
        cacheField.set(weatherCache, cache);

        WeatherInfo result = weatherCache.getWeatherInfo(city);

        assertNotNull(result);
        assertEquals(city, result.getCity());
        assertEquals(weatherInfo, result);

        verifyNoInteractions(weatherProvider);
    }

    /**
     * Тестовый сценарий для получения информации о погоде, когда она отсутствует в кэше и провайдер возвращает null.
     */
    @Test
    void getWeatherInfo_WeatherInfoNotInCache_ReturnsNullIfProviderReturnsNull() {
        String city = "dfghjfnhjf";
        when(weatherProvider.get(city)).thenReturn(null);
        WeatherInfo result = weatherCache.getWeatherInfo(city);
        assertNull(result);
        verify(weatherProvider).get(city);
    }

    /**
     * Тестовый сценарий для получения информации о погоде, когда город отсутствует и
     * провайдер возвращает код 404.
     */
    @Test
    void getWeatherInfo_CityNotFound_ThrowsRuntimeException() {
        String city = "nonexistentCity";
        when(weatherProvider.get(city)).thenThrow(new RuntimeException("City not found"));
        Exception exception = assertThrows(RuntimeException.class, () -> weatherCache.getWeatherInfo(city));
        assertEquals("City not found", exception.getMessage());
        verify(weatherProvider).get(city);
    }

    /**
     * Тестовый сценарий для удаления информации о погоде из кэша.
     */
    @Test
    void removeWeatherInfo_WeatherInfoInCache_RemovesWeatherInfoFromCache() {
        String city = "Tokyo";
        WeatherInfo weatherInfo = new WeatherInfo();
        weatherInfo.setCity(city);
        weatherInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        weatherInfo.setTemperature(25.5);
        weatherInfo.setWindSpeed(17.5);
        weatherInfo.setDescription("Тестовое описание");
        weatherInfo.setPressure(1000);
        weatherInfo.setShortDescription("тестовое короткое описание");

        Map<String, WeatherInfo> cache = new HashMap<>();
        cache.put(city, weatherInfo);

        try {
            java.lang.reflect.Field cacheField = WeatherCache.class.getDeclaredField("cache");
            cacheField.setAccessible(true);
            cacheField.set(weatherCache, cache);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        weatherCache.removeWeatherInfo(city);
        assertFalse(cache.containsKey(city));
    }

    /**
     * Тестовый сценарий для получения информации о погоде, когда в кэше присутствует устаревшая информация.
     * Ожидается, что будет загружена обновленная информация о погоде.
     */
    @Test
    void getWeatherInfo_ExpiredWeatherInfoInCache_ReturnsUpdatedWeatherInfo() {
        WeatherInfo outdatedWeatherInfo = new WeatherInfo();
        outdatedWeatherInfo.setCity("London");
        outdatedWeatherInfo.setExpiryTime(LocalDateTime.now().minusMinutes(1));
        outdatedWeatherInfo.setTemperature(18.0);
        outdatedWeatherInfo.setWindSpeed(8.0);
        outdatedWeatherInfo.setDescription("Устаревшее описание для Лондона");
        outdatedWeatherInfo.setPressure(1005);
        outdatedWeatherInfo.setShortDescription("устаревшее короткое описание для Лондона");

        WeatherInfo updatedWeatherInfo = new WeatherInfo();
        updatedWeatherInfo.setCity("London");
        updatedWeatherInfo.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        updatedWeatherInfo.setTemperature(20.0);
        updatedWeatherInfo.setWindSpeed(10.0);
        updatedWeatherInfo.setDescription("Обновленное описание для Лондона");
        updatedWeatherInfo.setPressure(1010);
        updatedWeatherInfo.setShortDescription("обновленное короткое описание для Лондона");

        when(weatherProvider.get("London")).thenReturn(updatedWeatherInfo);
        try {
            java.lang.reflect.Field cacheField = WeatherCache.class.getDeclaredField("cache");
            cacheField.setAccessible(true);
            Map<String, WeatherInfo> cache = new HashMap<>();
            cache.put("London", outdatedWeatherInfo);
            cacheField.set(weatherCache, cache);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        WeatherInfo result = weatherCache.getWeatherInfo("London");

        assertEquals(updatedWeatherInfo, result);
        verify(weatherProvider).get("London");
    }
}