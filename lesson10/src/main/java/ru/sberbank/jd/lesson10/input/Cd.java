package ru.sberbank.jd.lesson10.input;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import lombok.Data;

/**
 * Класс для хранения данных о диске.
 */
@Data
public class Cd {
    @JacksonXmlProperty(localName = "TITLE")
    private String title;
    @JacksonXmlProperty(localName = "ARTIST")
    private String artist;
    @JacksonXmlProperty(localName = "COUNTRY")
    private String country;
    @JacksonXmlProperty(localName = "COMPANY")
    private String company;
    @JacksonXmlProperty(localName = "PRICE")
    private double price;
    @JacksonXmlProperty(localName = "YEAR")
    private int year;
}
