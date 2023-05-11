package ru.sberbank.jd.lesson02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson02.exceptions.NodException;

public class NodImplTest {

    private final Nod nod = new NodImpl();

    @DisplayName("проверка базовых значений")
    @Test
    public void testCalculateBaseCase() {
        assertEquals(5, nod.calculate(5, 0));
    }

    @DisplayName("проверка аргументов 0 0")
    @Test
    public void testCalculateBothNumbersZero() {
        assertThrows(NodException.class, () -> nod.calculate(0, 0));
    }

    @DisplayName("проверка аргументов 1 1")
    @Test
    public void testCalculateBothNumbersOne() {
        assertEquals(1, nod.calculate(1, 1));
    }

    @DisplayName("проверка меньшего первого и большего второго аргументов")
    @Test
    public void testCalculateFirstLessThanSecond() {
        assertEquals(3, nod.calculate(9, 12));
    }

    @DisplayName("проверка большего первого и меньшего второго аргументов")
    @Test
    public void testCalculateFirstGreaterThanSecond() {
        assertEquals(4, nod.calculate(20, 12));
    }

    @DisplayName("проверка аргументов 0, MAX")
    @Test
    public void testCalculateFirstNumberOAndSecondNumberMax() {
        assertEquals(Integer.MAX_VALUE, nod.calculate(0, Integer.MAX_VALUE));
    }

    @DisplayName("проверка аргументов MIN,0")
    @Test
    public void testCalculateFirstNumberMINAndSecondNumber0() {
        assertEquals(Integer.MIN_VALUE, nod.calculate(Integer.MIN_VALUE, 0));
    }
    @DisplayName("проверка аргументов 0,MIN")
    @Test
    public void testCalculateFirstNumber0AndSecondNumberMIN() {
        assertEquals(Integer.MIN_VALUE, nod.calculate(0, Integer.MIN_VALUE));
    }
    @DisplayName("проверка аргументов MIN,MIN")
    @Test
    public void testCalculateFirstNumberMINAndSecondNumberMIN() {
        assertEquals(Integer.MIN_VALUE, nod.calculate(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }
}