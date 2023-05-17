package ru.sberbank.jd.lesson04.printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Класс InfoPrinter отвечает за печать информации о количестве слов и строк.
 */
public class InfoPrinter {

    /**
     * Выводит информацию о количестве слов для ввода с консоли.
     *
     * @param length Количество слов.
     */
    public int printWordsOrLinesInfo(int length) {
        System.out.println(length);
        return length;
    }

    /**
     * Выводит информацию о количестве слов для нескольких файлов.
     *
     * @param wordsInFiles HashMap, содержащая количество слов для каждого файла.
     * @return возвращает Map, в которой ключ количество подсчитанных строчек или слов. Значение всегда 0.
     */
    public Map<Integer, Integer> printWordsOrLinesInfo(Map<String, Integer> wordsInFiles) {
        Map<Integer, Integer> countWordsOrLinesInKey = printInfo(wordsInFiles);
        int totalLines;
        if (wordsInFiles.size() > 1) {
            totalLines = wordsInFiles.values().stream().mapToInt(Integer::intValue).sum();
            System.out.println(totalLines + "    " + " total");
        }
        return countWordsOrLinesInKey;
    }

    /**
     * Выводит информацию о количестве строк для одного файла.
     *
     * @param length Количество строк.
     * @return int количество строк.
     */
    public int printLinesInfo(int length) {
        System.out.println(length);
        return length;
    }

    /**
     * Вспомогательный метод для вывода информации о файле и количестве строк/слов.
     *
     * @param fileInfo HashMap с информацией о файле и количестве строк/слов в файле.
     */
    private Map<Integer, Integer> printInfo(Map<String, Integer> fileInfo) {
        int count = 0;
        for (Entry<String, Integer> entry : fileInfo.entrySet()) {
            count += fileInfo.get(entry.getKey());
            System.out.println(count + " " + entry.getKey());
        }
        return Map.of(count, 0);
    }

    /**
     * Выводит полную информацию о количестве слов и строк для нескольких файлов.
     *
     * @param wordsInfo HashMap с информацией о количестве слов для каждого файла.
     * @param linesInfo HashMap с информацией о количестве строк для каждого файла.
     * @return возвращает Map, в которой ключ количество подсчитанных строчек, а значение количество подсчитанных слов.
     */
    public Map<Integer, Integer> printAllInfo(Map<String, Integer> wordsInfo, Map<String, Integer> linesInfo) {
        int totalLines = 0;
        Map<String, List<Integer>> infoForPrint = new HashMap<>();
        for (Entry<String, Integer> entry : linesInfo.entrySet()) {
            int countLines = linesInfo.get(entry.getKey());
            totalLines += countLines;
            List<Integer> integerList = new ArrayList<>();
            integerList.add(countLines);
            infoForPrint.put(entry.getKey(), integerList);
        }
        int totalWords = 0;
        for (Entry<String, Integer> entry : wordsInfo.entrySet()) {
            int countWords = wordsInfo.get(entry.getKey());
            totalWords += countWords;
            infoForPrint.get(entry.getKey()).add(countWords);
        }
        for (String file : infoForPrint.keySet()) {
            System.out.println(infoForPrint.get(file).get(0) + "    " + infoForPrint.get(file).get(1) + " " + file);
        }
        if (infoForPrint.keySet().size() > 1) {
            System.out.println(totalLines + "    " + totalWords + " total");
        }
        return Map.of(totalLines, totalWords);
    }

    /**
     * Выводит информацию о приложении.
     *
     * @param help Строка с информацией о работе приложения.
     */
    public void printAppInfo(String help) {
        System.out.println(help);
    }
}
