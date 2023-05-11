package ru.sberbank.jd.lesson02.exceptions;


/**
 * Класс служит для обработки исключений, связанных с нахождением наибольшего общего делителя чисел.
 */
public class NodException extends RuntimeException {

    public NodException(String s) {
        super(s);
    }
}
