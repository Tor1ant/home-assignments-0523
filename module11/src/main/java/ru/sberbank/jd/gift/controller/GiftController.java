package ru.sberbank.jd.gift.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.sberbank.jd.gift.dto.GiftOutDto;
import ru.sberbank.jd.gift.dto.NewGiftDto;
import ru.sberbank.jd.gift.model.Gift;
import ru.sberbank.jd.gift.service.GiftService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/gift")
public class GiftController {

    private final GiftService giftService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Gift createGift(@RequestBody NewGiftDto newGiftDto) {
        return giftService.createGift(newGiftDto);
    }

    @GetMapping("/{id}")
    public GiftOutDto getGift(@PathVariable UUID id) {
        return giftService.getGift(id);
    }

    @PutMapping("/{id}")
    public Gift updateGift(@PathVariable UUID id, @RequestBody NewGiftDto newGiftDto) {
        return giftService.updateGift(id, newGiftDto);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteGift(@PathVariable UUID id) {
        giftService.deleteGift(id);
    }
}
