package ru.sberbank.jd.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class GiftOutDto {

    private  String title;

    private  String description;

    private  Double price;
}
