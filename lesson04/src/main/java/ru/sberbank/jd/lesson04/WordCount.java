package ru.sberbank.jd.lesson04;

import java.util.List;
import ru.sberbank.jd.lesson04.counter.WordCounter;
import ru.sberbank.jd.lesson04.parser.ArgsParser;
import ru.sberbank.jd.lesson04.printer.InfoPrinter;
import ru.sberbank.jd.lesson04.reader.CustomReader;

/**
 * Консольное приложение дублирующее команду wc.
 */
public class WordCount {

    /**
     * Главный метод программы.
     *
     * @param args массив String[]
     */
    public static void main(String[] args) {
        // В этом методе должны создаваться объекты классов из проекта
        // и вызываться их публичные методы.
        // Другого кода быть не должно
        ArgsParser argsParser = new ArgsParser();
        InfoPrinter infoPrinter = new InfoPrinter();
        WordCounter wordCounter = new WordCounter();
        wordCounter.setPrinter(infoPrinter);
        CustomReader reader = argsParser.parseArgs(List.of(args), "lesson04/");
        reader.read(wordCounter);
    }
}
