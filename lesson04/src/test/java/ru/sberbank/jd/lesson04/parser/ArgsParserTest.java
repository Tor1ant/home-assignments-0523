package ru.sberbank.jd.lesson04.parser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.util.List;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson04.reader.ConsoleReader;
import ru.sberbank.jd.lesson04.reader.CustomReader;
import ru.sberbank.jd.lesson04.reader.FileReader;

class ArgsParserTest {

    @Test
    void testParseArgs_existingFile() {
        List<String> args = List.of("1.txt", "1.txt");
        String baseDirectory = System.getProperty("user.dir");
        String absolutePath = baseDirectory + File.separator;
        String pathToFile = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
        CustomReader reader = new ArgsParser().parseArgs(args, absolutePath + pathToFile);
        assertTrue(reader instanceof FileReader);
    }

    @Test
    void testParseArgs_nonExistingFile() {
        String relativePath = "src/test/";
        List<String> args = List.of("3.txt", "4.txt");
        CustomReader reader = new ArgsParser().parseArgs(args, relativePath);
        assertTrue(reader instanceof ConsoleReader);
    }
}