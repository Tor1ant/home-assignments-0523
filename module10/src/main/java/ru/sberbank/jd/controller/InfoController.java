package ru.sberbank.jd.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.jd.config.HttpProperties;
import ru.sberbank.jd.model.Info;
import ru.sberbank.jd.service.GetService;

/**
 * Контроллер {@code InfoController}, представляет собой REST контроллер, который обрабатывает запросы на пути "/info".
 * <p>
 * Этот класс использует {@code GetService} для получения информации и {@code HttpProperties} для доступа к свойствам,
 * связанным с HTTP.
 * <p>
 * Конструктор для этого класса сгенерирован с помощью аннотации {@code RequiredArgsConstructor} Lombok, которая
 * автоматически генерирует конструктор для final полей.
 * <p>
 * Методы: {@code getInfo} - возвращает информацию, полученную от {@code GetService}.
 */
@RestController
@RequestMapping("/info")
@RequiredArgsConstructor
public class InfoController {

    private final HttpProperties httpProperties;
    private final GetService getService;

    /**
     * Обрабатывает GET запрос на "/info".
     *
     * @return информация, полученная от {@code GetService}.
     */
    @GetMapping
    public Info getInfo() {

        return getService.getInfo(httpProperties.getAdres());
    }
}
