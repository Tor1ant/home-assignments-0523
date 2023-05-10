package ru.sberbank.jd.lesson01;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class GreetingImplTest {

    Greeting greeting;

    @BeforeEach
    void setUp() {
        greeting = new GreetingImpl(
                "Вячеслав",
                "Ржеутский",
                1998,
                List.of("Компьютерные игры", "Право", "Программирование", "Аниме"),
                "https://bitbucket.org/javalerning/home-assignments-0523/src/master/",
                "8(999)-536-3450",
                List.of("От прохождения курса мне хотелось бы получить  приглашение на собеседование в команду"
                                + " разработки на позицию junior java разработчик.",
                        "Ожидаю от курса развитие компетенций, в частности обучение Spring security на уровне,"
                                + " необходимом для будущей работы.",
                        "В целом ожидаю приумножения текущих знаний и их углубление.")

        );
    }

    @DisplayName("Получение имени")
    @Test
    void getFirstNameTest() {
        Assertions.assertEquals("Вячеслав", greeting.getFirstName(), "Ожидается имя представляемого");
    }

    @DisplayName("Получение фамилии")
    @Test
    void getLastNameTest() {
        Assertions.assertEquals("Ржеутский", greeting.getLastName(), "Ожидается Фамилия"
                + " представляемого");
    }

    @DisplayName("Получение года рождения")
    @Test
    void getBirthYearTest() {
        Assertions.assertEquals(1998, greeting.getBirthYear());
    }

    @DisplayName("Получение списка Хобби")
    @Test
    void getHobbiesTest() {
        Assertions.assertEquals(List.of("Компьютерные игры", "Право", "Программирование", "Аниме"),
                greeting.getHobbies());
    }

    @DisplayName("Получение ссылки на битбакет rep")
    @Test
    void getBitbucketUrlTest() {
        Assertions.assertEquals("https://bitbucket.org/javalerning/home-assignments-0523/src/master/",
                greeting.getBitbucketUrl());
    }

    @DisplayName("Получение номера телефона")
    @Test
    void getPhoneTest() {
        Assertions.assertEquals("8(999)-536-3450", greeting.getPhone());
    }

    @DisplayName("Получение списка Хобби")
    @Test
    void getCourseExpectationsTest() {
        Assertions.assertEquals(
                List.of("От прохождения курса мне хотелось бы получить  приглашение на собеседование в команду"
                                + " разработки на позицию junior java разработчик.",
                        "Ожидаю от курса развитие компетенций, в частности обучение Spring security на уровне,"
                                + " необходимом для будущей работы.",
                        "В целом ожидаю приумножения текущих знаний и их углубление."),
                greeting.getCourseExpectations(), "должен вернутся список ожиданий от курса");
    }
}