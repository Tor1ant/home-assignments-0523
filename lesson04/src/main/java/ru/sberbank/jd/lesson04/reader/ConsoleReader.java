package ru.sberbank.jd.lesson04.reader;

import java.util.List;
import java.util.Scanner;
import java.util.function.Consumer;
import ru.sberbank.jd.lesson04.counter.WordCounter;
import ru.sberbank.jd.lesson04.exceptions.NotValidArgsException;

/**
 * Класс для считывания данных с консоли пользователя.
 */
public class ConsoleReader implements CustomReader {

    private final Scanner userInput;

    private final List<String> args;

    public ConsoleReader(List<String> args, Scanner inputStream) {
        this.args = args;
        userInput = inputStream;
    }

    /**
     * Читает ввод пользователя и вызывает соответствующие методы для обработки текста в зависимости от аргументов
     * программы.
     *
     * @param wordCounter объект класса worCounter, который будет вести подсчёт информации из консоли.
     * @return boolean true если метод отработал без ошибок и прочитал информацию из консоли.
     * @throws NotValidArgsException если переданные аргументы программы не могут быть обработаны.
     */
    @Override
    public boolean read(WordCounter wordCounter) {
        System.out.println("Введите текст: ");
        if (args.isEmpty()) {
            getInfo(wordCounter::getInfo);
            return true;
        }
        if (args.size() == 1) {
            switch (args.get(0)) {
                case "-w" -> getInfo(wordCounter::getWordInfoFromConsole);
                case "-l" -> getInfo(wordCounter::getLinesInfoFromConsole);
                case "-lw", "-wl" -> getInfo(wordCounter::getInfo);
                case "-help", "-version" -> wordCounter.getAppInfo(args.get(0));
                default -> throw new NotValidArgsException(
                        "аргумент программы: " + args.get(0) + " не может быть обработан.");
            }
        }
        if (args.size() == 2) {
            if (args.get(0).equals("-l") && args.get(1).equals("-w")
                    || args.get(0).equals("-w") && args.get(1).equals("-l")) {
                getInfo(wordCounter::getInfo);
            } else {
                throw new NotValidArgsException(
                        String.format("аргументы программы %s и %s не могут быть обработаны", args.get(0),
                                args.get(1)));
            }
        }
        return true;
    }

    private void getInfo(Consumer<String> infoGetter) {
        StringBuilder input = new StringBuilder();
        while (userInput.hasNextLine()) {
            input.append(userInput.nextLine()).append("\n");
        }
        infoGetter.accept(input.toString());
    }
}
