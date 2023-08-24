package ru.sberbank.jd.gift.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import ru.sberbank.jd.gift.dto.GiftOutDto;
import ru.sberbank.jd.gift.dto.NewGiftDto;
import ru.sberbank.jd.gift.model.Gift;

@SpringBootTest(properties = {
        "spring.config.name= application-test",
        "spring.config.location= classpath:application-test.yaml"
}, webEnvironment = WebEnvironment.NONE)
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
class GiftRepositoryImplTest {

    @Autowired
    private GiftRepository giftRepository;

    private NewGiftDto gift;

    @BeforeEach
    void setUp() {
        gift = new NewGiftDto("Тестовый подарок", "Тестовое описание", 1500.0);
    }

    @Test
    void createGift() {
        Gift savedGift = giftRepository.createGift(gift);
        assertThat(savedGift).isNotNull();
        assertThat(savedGift.getId()).isNotNull();
    }

    @Test
    void getGift() {
        Gift savedGift = giftRepository.createGift(gift);
        GiftOutDto foundGift = giftRepository.getGift(savedGift.getId());
        assertAll("Поля созданного и полученного подарков должны совпадать",
                () -> assertEquals(savedGift.getTitle(), foundGift.getTitle()),
                () -> assertEquals(savedGift.getDescription(), foundGift.getDescription()),
                () -> assertEquals(savedGift.getPrice(), foundGift.getPrice()));
    }

    @Test
    void updateGift() {

        Gift createdGift = giftRepository.createGift(gift);
        NewGiftDto giftForUpdate = new NewGiftDto("Обновленный тестовый подарок",
                "Обновленное тестовое описание", +
                3000.0);

        Gift updatedGift = giftRepository.updateGift(createdGift.getId(), giftForUpdate);

        assertAll("Поля созданного и полученного подарков должны совпадать",
                () -> assertEquals(giftForUpdate.getTitle(), updatedGift.getTitle()),
                () -> assertEquals(giftForUpdate.getDescription(), updatedGift.getDescription()),
                () -> assertEquals(giftForUpdate.getPrice(), updatedGift.getPrice()));
    }

    @Test
    void deleteGift() {
        Gift createdGift = giftRepository.createGift(gift);

        assertTrue(giftRepository.deleteGift(createdGift.getId()));
    }
}