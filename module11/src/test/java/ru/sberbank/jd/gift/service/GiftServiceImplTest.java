package ru.sberbank.jd.gift.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import ru.sberbank.jd.gift.dto.GiftOutDto;
import ru.sberbank.jd.gift.dto.NewGiftDto;
import ru.sberbank.jd.gift.model.Gift;
import ru.sberbank.jd.gift.repository.GiftRepository;

@SpringBootTest(properties = {
        "spring.config.name= application-test",
        "spring.config.location= classpath:application-test.yaml"
}, webEnvironment = WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class GiftServiceImplTest {

    @Autowired
    private GiftService giftService;

    private NewGiftDto newGiftDto;

    @BeforeEach
    void setUp() {
        newGiftDto = new NewGiftDto("Тестовый подарок", "Тестовое описание", 1500.0);
    }

    @DisplayName("Проверка создания подарка")
    @Test
    void createGift() {

        Gift gift1 = giftService.createGift(newGiftDto);

        assertAll("Поля созданного объекта должны совпадать с полями newGiftFto",
                () -> assertEquals(gift1.getTitle(), newGiftDto.getTitle()),
                () -> assertEquals(gift1.getDescription(), newGiftDto.getDescription()),
                () -> assertEquals(gift1.getPrice(), newGiftDto.getPrice()));
    }

    @DisplayName("Проверка получения подарка")
    @Test
    void getGift() {
        Gift createdGift = giftService.createGift(newGiftDto);

        GiftOutDto giftDto = giftService.getGift(createdGift.getId());

        assertAll("Поля созданного и полученного подарков должны совпадать",
                () -> assertEquals(createdGift.getTitle(), giftDto.getTitle()),
                () -> assertEquals(createdGift.getDescription(), giftDto.getDescription()),
                () -> assertEquals(createdGift.getPrice(), giftDto.getPrice()));
    }

    @Test
    void updateGift() {
        Gift createdGift = giftService.createGift(newGiftDto);
        NewGiftDto giftForUpdate = new NewGiftDto("Обновленный тестовый подарок",
                "Обновленное тестовое описание", +
                3000.0);

        Gift updatedGift = giftService.updateGift(createdGift.getId(), giftForUpdate);

        assertAll("Поля созданного и полученного подарков должны совпадать",
                () -> assertEquals(giftForUpdate.getTitle(), updatedGift.getTitle()),
                () -> assertEquals(giftForUpdate.getDescription(), updatedGift.getDescription()),
                () -> assertEquals(giftForUpdate.getPrice(), updatedGift.getPrice()));
    }

    @Test
    void deleteGift() {
        Gift createdGift = giftService.createGift(newGiftDto);

        assertTrue(giftService.deleteGift(createdGift.getId()));

    }
}