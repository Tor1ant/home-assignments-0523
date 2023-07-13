package ru.sberbank.jd.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.model.Info;

@Profile(value = "dev")
@Service
public class GetServiceImplStub implements GetService {

    @Override
    public Info getInfo(String http) {
        System.out.println("Вызван тестовый адрес= " + http);

        return new Info("Тестовая информация из Stub класса ", http);
    }
}
