package ru.sberbank.jd.lesson08.impl;

import java.util.Comparator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.sberbank.jd.lesson08.CustomTreeMap;

/**
 * Класс для тестирования методов класса {@link CustomTreeMapImpl}.
 */
public class CustomTreeMapImplTest {

    private CustomTreeMap<Integer, String> treeMap;

    /**
     * Выполняется перед каждым тестом для инициализации нового экземпляра CustomTreeMap.
     */
    @BeforeEach
    public void setUp() {
        Comparator<Integer> comparator = Comparator.naturalOrder();
        treeMap = new CustomTreeMapImpl<>(comparator);
    }

    /**
     * Тест проверяет метод size().
     * Проверяет, что размер карты соответствует ожидаемому значению после добавления и удаления элементов.
     */
    @Test
    public void testSize() {
        Assertions.assertEquals(0, treeMap.size());

        treeMap.put(1, "One");
        treeMap.put(2, "Two");

        Assertions.assertEquals(2, treeMap.size());

        treeMap.remove(1);

        Assertions.assertEquals(1, treeMap.size());
    }

    /**
     * Тест проверяет метод isEmpty().
     * Проверяет, что метод возвращает true, когда карта пуста, и false, когда в ней есть элементы.
     */
    @Test
    public void testIsEmpty() {
        Assertions.assertTrue(treeMap.isEmpty());

        treeMap.put(1, "One");

        Assertions.assertFalse(treeMap.isEmpty());

        treeMap.remove(1);

        Assertions.assertTrue(treeMap.isEmpty());
    }

    /**
     * Тест проверяет методы put() и get().
     * Проверяет, что добавленные элементы корректно сохраняются и возвращаются по ключу.
     */
    @Test
    public void testPutAndGet() {
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");

        Assertions.assertEquals("One", treeMap.get(1));
        Assertions.assertEquals("Two", treeMap.get(2));
        Assertions.assertEquals("Three", treeMap.get(3));
        Assertions.assertNull(treeMap.get(4));
    }

    /**
     * Тест проверяет метод remove().
     * Проверяет, что элемент корректно удаляется из карты и возвращается его значение.
     */
    @Test
    public void testRemove() {
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");

        Assertions.assertEquals(3, treeMap.size());

        String removedValue = treeMap.remove(2);

        Assertions.assertEquals("Two", removedValue);
        Assertions.assertEquals(2, treeMap.size());
        Assertions.assertNull(treeMap.get(2));
    }

    /**
     * Тест проверяет метод containsKey().
     * Проверяет, что метод возвращает true, когда элемент с указанным ключом существует, и false в противном случае.
     */
    @Test
    public void testContainsKey() {
        treeMap.put(1, "One");
        treeMap.put(2, "Two");

        Assertions.assertTrue(treeMap.containsKey(1));
        Assertions.assertTrue(treeMap.containsKey(2));
        Assertions.assertFalse(treeMap.containsKey(3));
    }

    /**
     * Тест проверяет метод containsValue().
     * Проверяет, что метод возвращает true, когда элемент с указанным значением существует, и false в противном случае.
     */
    @Test
    public void testContainsValue() {
        treeMap.put(1, "One");
        treeMap.put(2, "Two");

        Assertions.assertTrue(treeMap.containsValue("One"));
        Assertions.assertTrue(treeMap.containsValue("Two"));
        Assertions.assertFalse(treeMap.containsValue("Three"));
    }

    /**
     * Тест проверяет метод keys().
     * Проверяет, что метод возвращает массив всех ключей в карте.
     */
    @Test
    public void testKeys() {
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");

        Object[] keys = treeMap.keys();

        Assertions.assertEquals(3, keys.length);
        Assertions.assertArrayEquals(new Integer[]{1, 2, 3}, keys);
    }

    /**
     * Тест проверяет метод values().
     * Проверяет, что метод возвращает массив всех значений в карте.
     */
    @Test
    public void testValues() {
        treeMap.put(1, "One");
        treeMap.put(2, "Two");
        treeMap.put(3, "Three");

        Object[] values = treeMap.values();

        Assertions.assertEquals(3, values.length);
        Assertions.assertArrayEquals(new String[]{"One", "Two", "Three"}, values);
    }
}
