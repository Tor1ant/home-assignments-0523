package ru.sberbank.jd.gift.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewGiftDto {

    private String title;

    private String description;

    private Double price;
}
