package ru.sberbank.jd.lesson10.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * Класс для хранения данных об альбоме.
 */
@Data
@RequiredArgsConstructor(onConstructor_ = @JsonCreator)
@AllArgsConstructor()
public class Album implements Serializable {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private String name;

    @JacksonXmlProperty(isAttribute = true, localName = "year")
    private int year;
}
