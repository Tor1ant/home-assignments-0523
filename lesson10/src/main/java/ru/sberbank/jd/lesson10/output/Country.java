package ru.sberbank.jd.lesson10.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import lombok.RequiredArgsConstructor;


/**
 * Класс для хранения данных об артистах в рамках страны.
 */
@Data
@RequiredArgsConstructor()
public class Country implements Serializable {

    @JacksonXmlProperty(isAttribute = true, localName = "name")
    private final String name;

    @JacksonXmlProperty(localName = "Artist")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Artist> artists = new ArrayList<>();

    @JsonCreator
    public Country(@JacksonXmlProperty(localName = "name") @JsonProperty("name") String name,
            @JacksonXmlProperty(localName = "Artist") @JsonProperty("artists") List<Artist> artists) {
        this.name = name;
        this.artists = artists;
    }
}
