package ru.sberbank.jd.lesson10.input;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import java.util.List;
import lombok.Data;

/**
 * Класс для хранения данных каталога.
 */
@Data
@JacksonXmlRootElement
public class Catalog {
    @JacksonXmlProperty(localName = "CD")
    @JacksonXmlElementWrapper(useWrapping = false)
    private List<Cd> cds;
}
