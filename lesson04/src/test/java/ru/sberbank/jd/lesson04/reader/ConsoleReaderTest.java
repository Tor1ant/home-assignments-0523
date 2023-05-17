package ru.sberbank.jd.lesson04.reader;


import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson04.counter.WordCounter;
import ru.sberbank.jd.lesson04.exceptions.NotValidArgsException;
import ru.sberbank.jd.lesson04.printer.InfoPrinter;

/**
 * Класс ConsoleReaderTest содержит модульные тесты для класса ConsoleReader.
 */
class ConsoleReaderTest {

    private final InputStream systemIn = System.in;
    private final PrintStream systemOut = System.out;
    private ByteArrayInputStream testIn;
    private final WordCounter wordCounter = new WordCounter();

    @BeforeEach
    void setUp() {
        wordCounter.setPrinter(new InfoPrinter());
    }

    @AfterEach
    public void tearDown() {
        System.setIn(systemIn);
        System.setOut(systemOut);
    }

    /**
     * Тест метода read при передаче пустого массива аргументов.
     */
    @Test
    void testReadEmptyArgs() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] emptyArgs = {};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(emptyArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргументов "-l" и "-w" в произвольном порядке.
     */
    @Test
    void testReadLwArgs() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-l", "-w"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргументов "-w" и "-l" в произвольном порядке.
     */
    @Test
    void testReadWlArgs() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-w", "-l"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргумента "-wl" (сочетание двух флагов).
     */
    @Test
    void testReadWlike1arg() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-wl"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргумента "-l" (подсчет строк).
     */
    @Test
    void testReadLargs() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-l"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргумента "-lw" (подсчет строк и слов).
     */
    @Test
    void testReadLwike1arg() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-lw"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргумента "-l" и вводе текста с двумя строками.
     */
    @Test
    void testReadLargsWith2Lines() {
        String userInputString = "Тестовая строка 1" + "\r\n" + "Тестовая строка 2";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-l"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }


    /**
     * Тест метода read при передаче аргумента "-w" (подсчет слов).
     */
    @Test
    void testReadWargs() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-w"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        consoleReader.read(wordCounter);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче аргумента "-w" и вводе текста с двумя строками.
     */
    @Test
    void testReadWargsWith2Lines() {
        String userInputString = "Тестовая строка 1" + "\r\n" + "Тестовая строка 2";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-w"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        Assertions.assertTrue(consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче невалидного аргумента "-g".
     */
    @Test
    void testNotValidLarg() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-g"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        Assertions.assertThrows(NotValidArgsException.class, () -> consoleReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче нескольких невалидных аргументов.
     */
    @Test
    void testNotValidLargs() {
        String userInputString = "Тестовая строка 1";
        testIn = new ByteArrayInputStream(userInputString.getBytes());
        String[] lwArgs = {"-g", "-f"};
        Scanner scanner = new Scanner(testIn);
        ConsoleReader consoleReader = new ConsoleReader(List.of(lwArgs), scanner);
        Assertions.assertThrows(NotValidArgsException.class, () -> consoleReader.read(wordCounter));
    }
}
