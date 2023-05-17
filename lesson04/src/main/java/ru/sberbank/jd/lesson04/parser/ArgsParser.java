package ru.sberbank.jd.lesson04.parser;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import ru.sberbank.jd.lesson04.reader.ConsoleReader;
import ru.sberbank.jd.lesson04.reader.CustomReader;
import ru.sberbank.jd.lesson04.reader.FileReader;

/**
 * Класс для считывания аргументов приходящих в программу и дальнейшего их распределения в классы с бизнес-логикой.
 * Должен вызываться только в методе main
 */
public class ArgsParser {

    /**
     * Парсит аргументы командной строки и возвращает соответствующий объект CustomReader. Если файл с указанным именем
     * существует, возвращается FileReader, иначе возвращается ConsoleReader.
     *
     * @param args         Список аргументов командной строки, содержащих названия файлов.
     * @param relativePath Строка являющаяся путём к файлу.
     * @return Объект CustomReader для чтения данных либо из файла, либо с консоли.
     */
    public CustomReader parseArgs(List<String> args, String relativePath) {
        for (String filename : args) {
            File file = new File(relativePath + filename);
            if (file.exists()) {
                Path absolutePath = Paths.get(relativePath).toAbsolutePath();
                String baseDirectory = absolutePath.toString();
                return new FileReader(args, baseDirectory);
            }
        }
        return new ConsoleReader(args, new Scanner(System.in));
    }
}
