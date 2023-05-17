package ru.sberbank.jd.lesson04.counter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import ru.sberbank.jd.lesson04.printer.InfoPrinter;

/**
 * Класс для подсчета информации о словах и строках в текстовых файлах.
 */
public class WordCounter {

    private InfoPrinter infoPrinter;

    /**
     * Метод принимает {@link  InfoPrinter} объект и инициализирует поле infoprinter.
     *
     * @param infoPrinter объект InfoPrinter
     */
    public void setPrinter(InfoPrinter infoPrinter) {
        this.infoPrinter = infoPrinter;
    }

    /**
     * Получает информацию о словах и строках в переданной строке и выводит ее.
     *
     * @param s строка, содержащая текст.
     * @return Map ключём которой является количество слов, а значением количество строк в набранном в консоль тексте.
     */
    public Map<Integer, Integer> getInfo(String s) {
        return Map.of(getWordInfoFromConsole(s), getLinesInfoFromConsole(s));
    }

    /**
     * Получает информацию о словах и строках в переданных файлах, передаёт её в метод
     * {@link InfoPrinter#printAllInfo(Map, Map)}.
     *
     * @param filesInfo Map, где ключ - имя файла, значение - содержимое файла.
     * @param param     String - параметр запроса, который может принимать значения "-lw" "-wl" "-w" "-l"
     * @return возвращает Map, в которой ключ количество подсчитанных строчек, а значение количество подсчитанных слов.
     *     Для param -w и -l ключ является и количеством строчек и количеством слов. Значение всегда 0.
     */
    public Map<Integer, Integer> getInfo(Map<String, String> filesInfo, String param) {
        Map<String, Integer> wordsInfo = getInfoFromFile(filesInfo, "\\s+");
        Map<String, Integer> linesInfo = getInfoFromFile(filesInfo, "\n");
        Map<Integer, Integer> resultInfo = new HashMap<>();
        if (param.equals("-lw") || param.equals("-wl")) {
            resultInfo = infoPrinter.printAllInfo(wordsInfo, linesInfo);
        }
        if (param.equals("-w")) {
            resultInfo = infoPrinter.printWordsOrLinesInfo(wordsInfo);
        }
        if (param.equals("-l")) {
            resultInfo = infoPrinter.printWordsOrLinesInfo(linesInfo);
        }
        return resultInfo;
    }

    /**
     * Получает информацию о словах в переданной строке и передаёт её в метод
     * {@link InfoPrinter#printWordsOrLinesInfo(int)}.
     *
     * @param s строка, содержащая текст.
     * @return возвращает int количество слов в строке.
     */
    public int getWordInfoFromConsole(String s) {
        String[] words = s.split("\\s+");
        return infoPrinter.printWordsOrLinesInfo(words.length);
    }

    private Map<String, Integer> getInfoFromFile(Map<String, String> filesInfo, String regex) {
        File file;
        String filePath;
        HashMap<String, Integer> wordsInFiles = new HashMap<>();
        for (String filename : filesInfo.keySet()) {
            file = new File(filename);
            try {
                filePath = file.getCanonicalPath();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            String[] words = filesInfo.get(filename).split(regex);
            wordsInFiles.put(filePath, words.length);
        }
        return wordsInFiles;
    }

    /**
     * Получает информацию о словах в переданных файлах и передаёт её в метод *
     * {@link InfoPrinter#printLinesInfo(int)}.
     *
     * @param s строка, содержащая текст.
     */
    public int getLinesInfoFromConsole(String s) {
        String[] lines = s.split("\n");
        return infoPrinter.printLinesInfo(lines.length);
    }

    /**
     * Передаёт информацию о приложении в метод {@link InfoPrinter#printAppInfo(String)}.
     *
     * @param param String - параметр запроса, который должен быть в значении "-help".
     */
    public void getAppInfo(String param) {
        String info;
        if (param.equals("-help")) {
            info = "Приложение служит для подсчета строк и слов";
            infoPrinter.printAppInfo(info);
        }
        if (param.equals("-version")) {
            info = "Версия приложения 1.0" + "\n" + "Разработчик - Ржеутский В.С.";
            infoPrinter.printAppInfo(info);
        }

    }
}
