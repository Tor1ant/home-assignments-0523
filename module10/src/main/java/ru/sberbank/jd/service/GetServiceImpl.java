package ru.sberbank.jd.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sberbank.jd.model.Info;

@Service
@Profile(value = "prom")
@RequiredArgsConstructor
public class GetServiceImpl implements GetService {

    private final RestTemplate restTemplate;

    @Override
    public Info getInfo(String http) {

        return restTemplate.getForObject(http, Info.class);
    }
}
