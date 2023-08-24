package ru.sberbank.jd.gift.repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.sberbank.jd.exception.NotFoundException;
import ru.sberbank.jd.gift.dto.GiftOutDto;
import ru.sberbank.jd.gift.dto.NewGiftDto;
import ru.sberbank.jd.gift.model.Gift;

@Repository
public class GiftRepositoryImpl implements GiftRepository {

    private final Logger logger = LoggerFactory.getLogger(GiftRepositoryImpl.class);

    private final Map<String, Gift> uuidGiftMap = new HashMap<>();

    @Override
    public Gift createGift(NewGiftDto newGiftDto) {
        Gift newGift = Gift.builder().id(UUID.randomUUID())
                .price(newGiftDto.getPrice())
                .title(newGiftDto.getTitle())
                .description(newGiftDto.getDescription())
                .build();

        uuidGiftMap.put(newGift.getId().toString(), newGift);

        logger.info("Новый подарок= " + newGift + " добавлен.");

        return newGift;
    }

    @Override
    public GiftOutDto getGift(UUID giftId) {
        Gift gift = uuidGiftMap.get(giftId.toString());

        GiftOutDto giftOutDto = new GiftOutDto(gift.getTitle(), gift.getDescription(), gift.getPrice());

        logger.info("Подарок просмотрен= " + gift);

        return giftOutDto;
    }

    @Override
    public Gift updateGift(UUID giftId, NewGiftDto newGiftDto) {

        Gift updatedGift = uuidGiftMap.computeIfPresent(giftId.toString(),
                (id, gift) -> new Gift(UUID.fromString(id), newGiftDto.getTitle(), newGiftDto.getDescription(),
                        newGiftDto.getPrice()));

        if (updatedGift == null) {
            throw new NotFoundException("Подарок с id=" + giftId + " не найден");

        }
        logger.info("Подарок с id= " + giftId + " обновлён");

        return updatedGift;
    }


    @Override
    public boolean deleteGift(UUID giftId) {

        Gift removedGift = uuidGiftMap.remove(giftId.toString());

        if (removedGift == null) {
            throw new NotFoundException("Подарок с id=" + giftId + " не найден");

        }
        logger.info("Подарок с id= " + giftId + " удалён");

        return true;
    }
}
