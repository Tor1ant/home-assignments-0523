package ru.sberbank.jd.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.model.Info;

@ExtendWith(MockitoExtension.class)
class GetServiceTest {

    @Mock
    private RestTemplate template;

    @InjectMocks
    private GetServiceImpl getService;


    @DisplayName("Проверка получения информации с эндпоинта")
    @Test
    void testGetInfo() {
        Info info = new Info("Тестовый Инфо", "http://Test");
        when(template.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(info);
        Assertions.assertEquals(info, getService.getInfo("http://Test"));
        Mockito.verify(template, times(1)).getForObject(Mockito.anyString(), Mockito.any());
    }

    @DisplayName("Проверка возвращения null при отсутствии данных с эндпоинта")
    @Test
    void testGetInfoNoData() {
        when(template.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(null);
        Assertions.assertNull(getService.getInfo("http://Test"));
        Mockito.verify(template, times(1)).getForObject(Mockito.anyString(), Mockito.any());
    }

    @DisplayName("Проверка выброса исключения при проблемах с вызовом эндпоинта")
    @Test
    void testGetInfoThrowsException() {
        when(template.getForObject(Mockito.anyString(), Mockito.any())).thenThrow(new RuntimeException("Test exception"));
        Assertions.assertThrows(RuntimeException.class, () -> getService.getInfo("http://Test"));
        Mockito.verify(template, times(1)).getForObject(Mockito.anyString(), Mockito.any());
    }

    @DisplayName("Проверка вызова эндпоинта с различными URL")
    @Test
    void testGetInfoDifferentUrls() {
        Info info1 = new Info("Тестовый Инфо 1", "http://Test1");
        Info info2 = new Info("Тестовый Инфо 2", "http://Test2");
        when(template.getForObject("http://Test1", Info.class)).thenReturn(info1);
        when(template.getForObject("http://Test2", Info.class)).thenReturn(info2);
        Assertions.assertEquals(info1, getService.getInfo("http://Test1"));
        Assertions.assertEquals(info2, getService.getInfo("http://Test2"));
        Mockito.verify(template, times(1)).getForObject("http://Test1", Info.class);
        Mockito.verify(template, times(1)).getForObject("http://Test2", Info.class);
    }
}