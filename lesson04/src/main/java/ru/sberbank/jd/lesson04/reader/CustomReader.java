package ru.sberbank.jd.lesson04.reader;

import ru.sberbank.jd.lesson04.counter.WordCounter;

/**
 * Интерфейс CustomReader предоставляет метод чтения данных с помощью объекта WordCounter.
 */
public interface CustomReader {

    /**
     * Читает данные с использованием объекта WordCounter.
     *
     * @param wordCounter Объект WordCounter для обработки данных чтения.
     * @return возвращает true если метод выполнен успешно.
     */
    boolean read(WordCounter wordCounter);
}
