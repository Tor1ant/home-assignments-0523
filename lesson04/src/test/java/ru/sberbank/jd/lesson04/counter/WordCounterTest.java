package ru.sberbank.jd.lesson04.counter;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson04.printer.InfoPrinter;

/**
 * Класс WordCounterTest содержит модульные тесты для класса WordCounter.
 */
class WordCounterTest {

    private final WordCounter wordCounter = new WordCounter();
    private final PrintStream originalSystemOut = System.out;

    /**
     * Настройка необходимых ресурсов перед каждым тестом.
     */
    @BeforeEach
    void setUp() {
        wordCounter.setPrinter(new InfoPrinter());
    }

    /**
     * Восстановление исходного системного потока вывода после каждого теста.
     */
    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    /**
     * Тест метода getInfo на подсчет количества строк и слов в нескольких файлах.
     */
    @Test
    void getInfoTest() {
        Map<String, String> filesInfo = new HashMap<>();
        final String content1 = "Тестовая строка номер 1\nТестовая строка номер 2\nТестовая строка номер 3";
        final String content2 = "Тестовая строка номер 1\nТестовая строка номер 2\nТестовая строка номер 3";
        filesInfo.put("1.txt", content1);
        filesInfo.put("2.txt", content2);
        wordCounter.getInfo(filesInfo, "-lw");
        Assertions.assertEquals(Map.of(6, 24), wordCounter.getInfo(filesInfo, "-lw"));
    }

    /**
     * Тест метода getInfo на подсчет количества строк в нескольких файлах.
     */
    @Test
    void getLinesInfoTestFor2Files() {
        Map<String, String> filesInfo = new HashMap<>();
        final String content1 = "Тестовая строка номер 1\nТестовая строка номер 2\nТестовая строка номер 3";
        final String content2 = "Тестовая строка номер 1\nТестовая строка номер 2\nТестовая строка номер 3";
        filesInfo.put("1.txt", content1);
        filesInfo.put("2.txt", content2);
        Assertions.assertEquals(Map.of(6, 0), wordCounter.getInfo(filesInfo, "-l"));
    }

    /**
     * Тест метода getInfo на подсчет количества слов в одном файле.
     */
    @Test
    void getWordsInfoTestFor1File() {
        Map<String, String> filesInfo = new HashMap<>();
        Map<Integer, Integer> expected = Map.of(12, 0);
        final String content1 = "Тестовая строка номер 1\nТестовая строка номер 2\nТестовая строка номер 3";
        filesInfo.put("1.txt", content1);
        Assertions.assertEquals(expected, wordCounter.getInfo(filesInfo, "-w"));
    }

    /**
     * Тест метода getInfo на получение информации из консоли.
     */
    @Test
    void getInfoFromConsoleTest() {
        Map<Integer, Integer> expected = Map.of(4, 1);
        wordCounter.getInfo("Тестовая строка номер 1");
        Assertions.assertEquals(expected, wordCounter.getInfo("Тестовая строка номер 1"));
    }
}
