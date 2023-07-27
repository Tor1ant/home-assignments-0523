package ru.sberbank.jd.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.model.Info;


class GetServiceImplStubTest {

    private final GetService getService = new GetServiceImplStub();

    @DisplayName("Проверка возвращаемой информации из Stub сервиса")
    @Test
    void testGetInfo() {
        String http = "http://Test";
        Info expectedInfo = new Info("Тестовая информация из Stub класса ", http);
        Info actualInfo = getService.getInfo(http);
        Assertions.assertEquals(expectedInfo, actualInfo);
    }

    @DisplayName("Проверка значения URL в возвращаемой информации")
    @Test
    void testGetInfoUrl() {
        String http = "http://Test";
        Info info = getService.getInfo(http);
        Assertions.assertEquals(http, info.url());
    }

    @DisplayName("Проверка значения текста в возвращаемой информации")
    @Test
    void testGetInfoText() {
        String http = "http://Test";
        Info info = getService.getInfo(http);
        Assertions.assertEquals("Тестовая информация из Stub класса ", info.origin());
    }
}