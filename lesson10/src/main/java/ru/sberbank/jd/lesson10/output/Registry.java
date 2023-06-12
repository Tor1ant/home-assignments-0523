package ru.sberbank.jd.lesson10.output;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

/**
 * Класс для хранения данных по странам.
 */
@Data
@JacksonXmlRootElement(localName = "ArtistRegistry")
public class Registry implements Serializable {

    @JacksonXmlProperty(isAttribute = true, localName = "countryCount")
    @JsonProperty("countryCount")
    private int countryCount;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "Country")
    private List<Country> countries;

    @JsonCreator()
    public Registry() {
    }

    @JsonCreator
    public Registry(@JacksonXmlProperty(isAttribute = true, localName = "countryCount")
            @JsonProperty("countryCount") int countryCount,
            @JacksonXmlProperty(localName = "Country")
            @JsonProperty("countries") List<Country> countries) {
        this.countryCount = countryCount;
        this.countries = countries;
    }
}
