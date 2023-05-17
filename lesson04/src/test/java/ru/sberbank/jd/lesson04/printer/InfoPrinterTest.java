package ru.sberbank.jd.lesson04.printer;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Класс тестирующий {@link InfoPrinter}.
 */
class InfoPrinterTest {

    private final PrintStream originalSystemOut = System.out;

    private InfoPrinter infoPrinter;

    /**
     * Метод перенаправляющий вывод с консоли в массив байт.
     */
    @BeforeEach
    void setUp() {
        infoPrinter = new InfoPrinter();
    }

    /**
     * Метод перенаправляющий вывод с массива байт на консоль.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    /**
     * Метод тестирующий вывод в консоль количество слов в строке, которую ввёл пользователь.
     */
    @Test
    void testPrintWordsInfoFromConsole() {
        Assertions.assertEquals(7, infoPrinter.printWordsOrLinesInfo(7));
    }

    /**
     * Метод тестирующий вывод в консоль количество строк, введённых пользователем.
     */
    @Test
    void testPrintLinesInfoFromConsole() {
        Assertions.assertEquals(7, infoPrinter.printLinesInfo(7));
    }

    /**
     * Метод тестирующий вывод в консоль количество строк, содержащихся в файле.
     */
    @Test
    void testPrintLinesInfoFromFile() {
        Map<String, Integer> linesInFile = new HashMap<>();
        String file = "lesson04/src/main/java/ru/sberbank/jd/lesson04/1.txt";
        linesInFile.put(file, 10);
        Assertions.assertEquals(Map.of(10, 0), infoPrinter.printWordsOrLinesInfo(linesInFile));
    }

    /**
     * Метод тестирующий вывод в консоль количество слов, содержащихся в файле.
     */
    @Test
    void testPrintWordsInfoFromFile() {
        Map<String, Integer> wordsInFile = new HashMap<>();
        String file = "lesson04/src/main/java/ru/sberbank/jd/lesson04/1.txt";
        wordsInFile.put(file, 10);
        infoPrinter.printWordsOrLinesInfo(wordsInFile);
        Assertions.assertEquals(Map.of(10, 0), infoPrinter.printWordsOrLinesInfo(wordsInFile));
    }

    /**
     * Метод тестирующий вывод в консоль количество слов и строк содержащихся в файле.
     */
    @Test
    void printAllInfoFromFileTest() {
        Map<String, Integer> linesInFile = new HashMap<>();
        Map<String, Integer> wordsInFile = new HashMap<>();
        String file = "lesson04/src/main/java/ru/sberbank/jd/lesson04/1.txt";
        linesInFile.put(file, 10);
        wordsInFile.put(file, 10);
        Assertions.assertEquals(Map.of(10, 10), infoPrinter.printAllInfo(wordsInFile, linesInFile));
    }
}
