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
 * Класс для хранения данных об исполнителе.
 */
@Data
@RequiredArgsConstructor(onConstructor_ = @JsonCreator)
public class Artist implements Serializable {
    @JacksonXmlProperty(localName = "Name")
    private final String name;

    @JacksonXmlProperty(localName = "Album")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Album> albums = new ArrayList<>();

    @JsonCreator
    public Artist(@JacksonXmlProperty(localName = "Name") @JsonProperty("name") String name,
                   @JacksonXmlProperty(localName = "Album") @JsonProperty("albums") List<Album> albums) {
        this.name = name;
        this.albums = albums;
    }
}
