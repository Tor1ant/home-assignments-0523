package ru.sberbank.jd.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * Класс {@code HttpProperties} служит для связывания свойств с префиксом "http" из файла конфигурации приложения
 * (например, application.properties) с полями этого класса.
 * <p>
 * Этот компонент активен только в профилях "prom" и "dev".
 * <p>
 * С помощью аннотаций {@code Getter} и {@code Setter} от Lombok автоматически генерируются геттеры и сеттеры для полей
 * класса.
 * <p>
 * Поля: {@code adres} - используется для хранения IP-адреса или имени хоста.
 */
@ConfigurationProperties(prefix = "http")
@Getter
@Setter
@Component
@Profile({"prom", "dev"})
public class HttpProperties {

    private String adres;
}
