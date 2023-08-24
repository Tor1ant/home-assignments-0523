package ru.sberbank.jd.gift.model;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class Gift {

    private UUID id;

    private final String title;

    private final String description;

    private final Double price;
}
