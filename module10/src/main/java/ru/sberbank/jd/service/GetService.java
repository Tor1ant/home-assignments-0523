package ru.sberbank.jd.service;

import ru.sberbank.jd.model.Info;

/**
 * Интерфейс {@code GetService} предоставляет контракт для сервиса, который получает информацию из внешнего источника
 * по-данному URL.
 * <p>
 * Методы интерфейса: {@code getInfo} - должен быть реализован для получения информации из внешнего источника.
 */
public interface GetService {

    /**
     * Получает информацию из внешнего источника по-заданному URL.
     *
     * @param http - URL, откуда будет получена информация.
     * @return информацию, полученную из внешнего источника.
     */
    Info getInfo(String http);
}
