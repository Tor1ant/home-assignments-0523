package ru.sberbank.jd.lesson02;

import ru.sberbank.jd.lesson02.exceptions.NodException;

/**
 * Класс имеет один метод {@link NodImpl#calculate(int, int)}, который принимает аргументами 2 int числа и возвращет
 * наибольший общий делитель аргументов в виде int числа.
 */

public class NodImpl implements Nod {

    /**
     * Метод использует алгоритм Евклида и рекурсию для нахождения наибольшего общего делителя.
     *
     * @param first  первое число.
     * @param second второе число.
     * @return наибольший общий делитель двух аргументов метода в виде примитива int.
     */
    @Override
    public int calculate(int first, int second) {
        if (first == 0 && second == 0) {
            throw new NodException(" 0 и 0 не имеют наибольшего общего делителя");
        }
        if (second == 0) {
            return first;
        }
        if (first == 0) {
            return second;
        }
        return calculate(second, first % second);
    }
}
