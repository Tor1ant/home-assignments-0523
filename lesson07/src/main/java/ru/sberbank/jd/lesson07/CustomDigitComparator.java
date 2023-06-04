package ru.sberbank.jd.lesson07;

import java.util.Comparator;

/**
 * Класс, определяющий порядок четных и нечетных чисел.
 */
public class CustomDigitComparator implements Comparator<Integer> {


    /**
     * Сравнивает два целых числа, определяя порядок сначала четных, затем нечетных чисел.
     *
     * @param o1 первое целое число для сравнения
     * @param o2 второе целое число для сравнения
     * @return отрицательное целое число, если o1 четное и o2 нечетное; положительное целое число, если o1 нечетное и o2
     *     четное; ноль, если оба числа четные или оба нечетные
     * @throws IllegalArgumentException если один из аргументов равен null
     */
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 == null || o2 == null) {
            throw new IllegalArgumentException("аргументы метода не могут быть null");
        }
        return (o1 & 1) - (o2 & 1);
    }
}
