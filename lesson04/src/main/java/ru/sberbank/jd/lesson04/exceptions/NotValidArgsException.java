package ru.sberbank.jd.lesson04.exceptions;

/**
 * Класс исключения, которое выбрасывается, если программа запускается с не верными параметрами.
 */
public class NotValidArgsException extends RuntimeException {

    public NotValidArgsException(String s) {
        super(s);
    }
}
