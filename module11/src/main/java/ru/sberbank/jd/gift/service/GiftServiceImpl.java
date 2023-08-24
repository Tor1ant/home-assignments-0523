package ru.sberbank.jd.gift.service;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sberbank.jd.gift.dto.GiftOutDto;
import ru.sberbank.jd.gift.dto.NewGiftDto;
import ru.sberbank.jd.gift.model.Gift;
import ru.sberbank.jd.gift.repository.GiftRepository;

@Service
@RequiredArgsConstructor
public class GiftServiceImpl implements GiftService {

    private final GiftRepository giftRepository;

    @Override
    public Gift createGift(NewGiftDto newGiftDto) {
        return giftRepository.createGift(newGiftDto);
    }

    @Override
    public GiftOutDto getGift(UUID giftId) {
        return giftRepository.getGift(giftId);
    }

    @Override
    public Gift updateGift(UUID uuid, NewGiftDto newGiftDto) {
        return giftRepository.updateGift(uuid, newGiftDto);
    }

    @Override
    public boolean deleteGift(UUID giftId) {
        return giftRepository.deleteGift(giftId);
    }
}
