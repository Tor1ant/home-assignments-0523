package ru.sberbank.jd.lesson10.exceptions;

/**
 * {@code FileNotFoundException} является исключением времени выполнения, которое указывает на то, что файл не был
 * найден.
 * Обычно оно выбрасывается, когда операция ожидает наличие файла по определенному пути, но файл не найден.
 */
public class FileNotFoundException extends RuntimeException {

    /**
     * Конструирует новый объект {@code FileNotFoundException} с указанным сообщением об ошибке.
     *
     * @param message сообщение об ошибке
     */
    public FileNotFoundException(String message) {
        super(message);
    }
}
