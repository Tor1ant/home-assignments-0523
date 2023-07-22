package ru.sberbank.jd.gift.service;

import java.util.UUID;
import ru.sberbank.jd.gift.dto.GiftOutDto;
import ru.sberbank.jd.gift.dto.NewGiftDto;
import ru.sberbank.jd.gift.model.Gift;

public interface GiftService {

    Gift createGift(NewGiftDto newGiftDto);

    GiftOutDto getGift(UUID giftId);

    Gift updateGift(UUID uuid, NewGiftDto newGiftDto);

    boolean deleteGift(UUID giftId);
}
