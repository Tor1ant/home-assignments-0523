package ru.sberbank.jd.lesson02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson02.exceptions.NodException;

/**
 * Класс для тестирования класса {@link NodImpl}.
 */
public class NodImplTest {

    private final Nod nod = new NodImpl();

    @DisplayName("проверка аргументов 0 0")
    @Test
    public void testCalculateBothNumbersZero() {
        assertThrows(NodException.class, () -> nod.calculate(0, 0));
    }

    @DisplayName("проверка произвольного  аргумента и MIN")
    @Test
    public void testCalculateFirstLessThanSecond() {
        assertEquals(4, nod.calculate(12, Integer.MIN_VALUE));
    }

    @DisplayName("проверка MIN и произвольного  аргумента")
    @Test
    public void testCalculateFirstMinAndSecond12() {
        assertEquals(4, nod.calculate(Integer.MIN_VALUE, 12));
    }

    @DisplayName("проверка MAX и произвольного  аргумента")
    @Test
    public void testCalculateFirstMaxAndSecond12() {
        assertEquals(1, nod.calculate(Integer.MAX_VALUE, 12));
    }

    @DisplayName("проверка произвольного  аргумента и MAX")
    @Test
    public void testCalculateFirst12AndSecondMax() {
        assertEquals(1, nod.calculate(12, Integer.MAX_VALUE));
    }

    @DisplayName("проверка аргументов MAX, 0")
    @Test
    public void testCalculateFirstNumberMaxAndSecondNumber0() {
        assertEquals(Integer.MAX_VALUE, nod.calculate(Integer.MAX_VALUE, 0));
    }

    @DisplayName("проверка аргументов 0, MAX")
    @Test
    public void testCalculateFirstNumberZeroAndSecondNumberMax() {
        assertEquals(Integer.MAX_VALUE, nod.calculate(0, Integer.MAX_VALUE));
    }

    @DisplayName("проверка аргументов MIN,0")
    @Test
    public void testCalculateFirstNumberMinAndSecondNumber0() {
        assertEquals(Integer.MIN_VALUE, nod.calculate(Integer.MIN_VALUE, 0));
    }

    @DisplayName("проверка аргументов 0,MIN")
    @Test
    public void testCalculateFirstNumber0AndSecondNumberMin() {
        assertEquals(Integer.MIN_VALUE, nod.calculate(0, Integer.MIN_VALUE));
    }

    @DisplayName("проверка аргументов MIN,MIN")
    @Test()
    public void testCalculateFirstNumberMinAndSecondNumberMin() {
        Assertions.assertThrows(NodException.class, () -> nod.calculate(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    @DisplayName("проверка аргументов MAX,MAX")
    @Test
    public void testCalculateFirstNumberMaxAndSecondNumberMax() {
        assertEquals(Integer.MAX_VALUE, nod.calculate(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}