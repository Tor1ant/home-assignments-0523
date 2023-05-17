package ru.sberbank.jd.lesson04.reader;

import java.io.File;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson04.counter.WordCounter;
import ru.sberbank.jd.lesson04.exceptions.NotValidArgsException;
import ru.sberbank.jd.lesson04.printer.InfoPrinter;

/**
 * Класс FileReaderTest содержит модульные тесты для класса FileReader.
 */
class FileReaderTest {

    private final PrintStream originalSystemOut = System.out;
    private final WordCounter wordCounter = new WordCounter();

    @BeforeEach
    void setUp() {
        wordCounter.setPrinter(new InfoPrinter());
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalSystemOut);
    }

    /**
     * Тест метода read при отсутствии переданных параметров.
     */

    @Test
    void readWithoutParamTest() {
        String[] args = {"1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertTrue(fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче параметра "-l".
     */

    @Test
    void readWithLparamTest() {
        String[] args = {"-l", "1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertTrue(fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче неверного пути к файлу.
     */

    @Test
    void readNotFoundFileTest() {
        String[] args = {"-l", "1.txt"};
        String absolutePath = "lesson04";
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertThrows(RuntimeException.class, () -> fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче параметра "-w".
     */

    @Test
    void readWithWparamTest() {
        String[] args = {"-w", "1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertTrue(fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче невалидного аргумента.
     */

    @Test
    void readWithInvalidParamTest() {
        String[] args = {"-g", "1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertThrows(NotValidArgsException.class, () -> fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче параметров "-w" и "-l".
     */

    @Test
    void readWithWlParamsTest() {
        String[] args = {"-w", "-l", "1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertTrue(fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче параметров "-w", "-l" и двух файлов.
     */

    @Test
    void readWithWlParamsAnd2FilesTest() {
        String[] args = {"-w", "-l", "1.txt", "2.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertTrue(fileReader.read(wordCounter));
    }

    /**
     * Тест метода read при передаче невалидных параметров.
     */

    @Test
    void readWithInvalidParamsTest() {
        String[] args = {"-j", "-g", "1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertThrows(NotValidArgsException.class, () -> fileReader.read(wordCounter));
    }


    /**
     * Тест метода read при передаче параметра -help.
     */


    @Test
    void readWithHelpParam() {
        String[] args = {"-help", "1.txt"};
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        FileReader fileReader = new FileReader(List.of(args), absolutePath + pathToFile);
        Assertions.assertTrue(fileReader.read(wordCounter));
    }
}


