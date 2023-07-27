package ru.sberbank.jd.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.client.RestTemplate;

/**
 * Этот класс обеспечивает определение настроек конфигурации для данного приложения. Он используется для создания бинов Spring,
 * которые необходимы для выполнения приложения. Объекты, которые создаются этими методами, автоматически добавляются в контекст
 * приложения Spring и могут быть внедрены в любой компонент или сервис приложения.
 */
@Configuration
public class ConfigurationProperties {

    /**
     * Создает {@link RestTemplate}, который является центральным классом для клиентских HTTP-запросов.
     * Он используется для отправки HTTP-запросов и получения HTTP-ответов.
     * Этот бин имеет профиль "prom", что означает, что он доступен только когда данный профиль активен.
     *
     * @return новый экземпляр RestTemplate
     */
    @Bean
    @Profile(value = "prom")
    public  RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
