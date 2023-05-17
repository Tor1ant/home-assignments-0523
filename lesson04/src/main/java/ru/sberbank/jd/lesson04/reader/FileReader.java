package ru.sberbank.jd.lesson04.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import ru.sberbank.jd.lesson04.counter.WordCounter;
import ru.sberbank.jd.lesson04.exceptions.NotValidArgsException;

/**
 * Реализация интерфейса ArgsReader для чтения аргументов и обработки файлов.
 */
public class FileReader implements CustomReader {

    private final List<String> param;
    private final List<String> files;
    private final String pathToFile;

    /**
     * Конструктор класса FileReader.
     *
     * @param args       список аргументов программы.
     * @param pathToFile строка являющаяся путём к файлу.
     */
    public FileReader(List<String> args, String pathToFile) {
        this.files = args.stream().filter(s -> s.contains(".")).collect(Collectors.toList());
        this.param = args.stream().filter(s -> s.startsWith("-")).collect(Collectors.toList());
        this.pathToFile = pathToFile;
    }

    /**
     * Читает файлы, формирует информацию о содержимом файлов и вызывает соответствующие методы для обработки текста в
     * зависимости от аргументов программы.
     *
     * @param wordCounter передаётся в метод для обработки информации о словах и строках в файле.
     * @return возвращает true если информация из файла успешно прочитана.
     * @throws NotValidArgsException если переданные аргументы программы не могут быть обработаны.
     * @throws RuntimeException      если возникла ошибка при чтении файлов.
     */
    public boolean read(WordCounter wordCounter) {
        Map<String, String> filesInfo = new HashMap<>();
        for (String fileName : files) {
            StringBuilder stringsFromFile = new StringBuilder();
            try {
                File file = new File(pathToFile + "/" + fileName);
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                while (bufferedReader.ready()) {
                    stringsFromFile.append(bufferedReader.readLine())
                            .append("\n");
                }
                filesInfo.put(fileName, stringsFromFile.toString());
                bufferedReader.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (param.isEmpty()) {
            wordCounter.getInfo(filesInfo, "-lw");
            return true;
        }
        if (param.size() == 1) {
            switch (param.get(0)) {
                case "-w", "-l", "-lw", "-wl" -> wordCounter.getInfo(filesInfo, param.get(0));
                case "-help", "-version" -> wordCounter.getAppInfo(param.get(0));
                default -> throw new NotValidArgsException(
                        "аргумент программы: " + param.get(0) + " не может быть обработан.");
            }
        }
        if (param.size() == 2) {
            if (param.get(0).equals("-l") && param.get(1).equals("-w")
                    || param.get(0).equals("-w") && param.get(1).equals("-l")) {
                wordCounter.getInfo(filesInfo, "-wl");
            } else {
                throw new NotValidArgsException(
                        String.format("аргументы программы %s и %s не могут быть обработаны", param.get(0),
                                param.get(1)));
            }
        }
        return true;
    }
}
