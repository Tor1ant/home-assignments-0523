package ru.sberbank.jd.lesson12.model;


import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Класс, отражающий структуру хранимых в таблице полей.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class Student {

    /*
     * Первичный ключ.
     *
     * Рекомендуется генерировать его только внутри StudentsRepositoryCRUD.create(),
     * иными словами до момента пока объект не будет сохранен в БД, он не должен
     * иметь значение id.
     */
    private UUID id;
    private final String firstName;
    private final String lastName;
    private  Date birthDate;
    private final boolean isGraduated;
}
