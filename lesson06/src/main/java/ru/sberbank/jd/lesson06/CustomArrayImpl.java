package ru.sberbank.jd.lesson06;

import java.util.Arrays;
import java.util.Collection;
import java.util.Objects;

/**
 * CustomArrayImpl реализует интерфейс CustomArray, используя массив в качестве основной структуры данных. Он
 * предоставляет функциональность, аналогичную классу ArrayList в Java Collections Framework.
 */
public class CustomArrayImpl<T> implements CustomArray<T> {

    private static final int DEFAULT_CAPACITY = 10;

    private Object[] elements;
    private int size;

    public CustomArrayImpl() {
        this.elements = new Object[DEFAULT_CAPACITY];
        this.size = 0;
    }

    /**
     * Конструирует CustomArrayImpl с указанной начальной вместимостью.
     *
     * @param initialCapacity начальная вместимость массива
     * @throws IllegalArgumentException если начальная вместимость отрицательная
     */
    public CustomArrayImpl(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Вместительность листа не может быть меньше 0: " + initialCapacity);
        }
        this.elements = new Object[initialCapacity];
        this.size = 0;
    }

    /**
     * Конструктор, создающий объект CustomArrayImpl на основе переданной коллекции.
     *
     * @param c коллекция, на основе которой будет создан CustomArrayImpl
     * @throws IllegalArgumentException если переданная коллекция равна null
     */
    public CustomArrayImpl(Collection<T> c) {
        if (c == null) {
            throw new IllegalArgumentException("Коллекция не может быть null");
        }
        int size = c.size();
        this.elements = new Object[size];
        this.size = size;
        int index = 0;
        for (T element : c) {
            this.elements[index] = element;
            index++;
        }
    }


    /**
     * Возвращает количество элементов в массиве.
     *
     * @return количество элементов в массиве
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Проверяет, является ли массив пустым.
     *
     * @return true, если массив пустой, false в противном случае
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Добавляет один элемент в массив.
     *
     * @param item элемент, который нужно добавить
     * @return true, если элемент успешно добавлен, false в противном случае
     */
    @Override
    public boolean add(T item) {
        ensureCapacity(size + 1);
        elements[size++] = item;
        return true;
    }

    /**
     * Добавляет все элементы из массива в конец массива.
     *
     * @param items массив элементов, которые нужно добавить
     * @return true, если все элементы успешно добавлены, false в противном случае
     * @throws IllegalArgumentException если параметр items равен null
     */
    @Override
    public boolean addAll(T[] items) {
        if (items == null) {
            throw new IllegalArgumentException("массив не может быть null");
        }
        ensureCapacity(size + items.length);
        System.arraycopy(items, 0, elements, size, items.length);
        size += items.length;
        return true;
    }

    /**
     * Добавляет все элементы из коллекции в конец массива.
     *
     * @param items коллекция элементов, которые нужно добавить
     * @return true, если все элементы успешно добавлены, false в противном случае
     * @throws IllegalArgumentException если параметр items равен null
     */
    @Override
    @SuppressWarnings("unchecked")
    public boolean addAll(Collection<T> items) {
        if (items == null) {
            throw new IllegalArgumentException("коллекция не может быть null");
        }
        return addAll((T[]) items.toArray());
    }

    /**
     * Добавляет все элементы в текущую позицию массива.
     *
     * @param index индекс, на котором нужно вставить элементы
     * @param items элементы, которые нужно вставить
     * @return true, если все элементы успешно добавлены, false в противном случае
     * @throws ArrayIndexOutOfBoundsException если индекс выходит за пределы массива
     * @throws IllegalArgumentException       если параметр items равен null
     */
    @Override
    public boolean addAll(int index, T[] items) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException("Индекс за границами массива");
        }
        if (items == null) {
            throw new IllegalArgumentException("массив не может быть null");
        }
        ensureCapacity(size + items.length);
        System.arraycopy(elements, index, elements, index + items.length, size - index);
        System.arraycopy(items, 0, elements, index, items.length);
        size += items.length;
        return true;
    }

    /**
     * Возвращает элемент по индексу.
     *
     * @param index индекс элемента
     * @return элемент по указанному индексу
     * @throws ArrayIndexOutOfBoundsException если индекс выходит за пределы массива
     */
    @Override
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkBounds(index);
        return (T) elements[index];
    }


    /**
     * Заменяет элемент по индексу новым элементом.
     *
     * @param index индекс элемента
     * @param item  новый элемент
     * @return старое значение элемента по указанному индексу
     * @throws ArrayIndexOutOfBoundsException если индекс выходит за пределы массива
     */
    @Override
    @SuppressWarnings("unchecked")
    public T set(int index, T item) {
        checkBounds(index);
        T oldValue = (T) elements[index];
        elements[index] = item;
        return oldValue;
    }


    /**
     * Удаляет элемент по индексу.
     *
     * @param index индекс элемента
     * @throws ArrayIndexOutOfBoundsException если индекс выходит за пределы массива
     */
    @Override
    public void remove(int index) {
        checkBounds(index);
        System.arraycopy(elements, index + 1, elements, index, size - index - 1);
        elements[--size] = null;
    }

    /**
     * Удаляет первое вхождение элемента из массива.
     *
     * @param item элемент, который нужно удалить
     * @return true, если элемент успешно удален, false в противном случае
     */
    @Override
    public boolean remove(T item) {
        int index = indexOf(item);
        if (index != -1) {
            remove(index);
            return true;
        }
        return false;
    }

    /**
     * Проверяет, содержит ли массив указанный элемент.
     *
     * @param item элемент, который нужно проверить
     * @return true, если элемент существует, false в противном случае
     */
    @Override
    public boolean contains(T item) {
        return indexOf(item) != -1;
    }

    /**
     * Возвращает индекс первого вхождения элемента в массиве.
     *
     * @param item элемент, индекс которого нужно найти
     * @return индекс элемента или -1, если элемент не найден
     */
    @Override
    public int indexOf(T item) {
        for (int i = 0; i < size; i++) {
            if (Objects.equals(item, elements[i])) {
                return i;
            }
        }

        return -1;
    }

    /**
     * Увеличивает вместимость массива для хранения новых элементов, если это необходимо.
     *
     * @param newElementsCount количество новых элементов
     */
    @Override
    public void ensureCapacity(int newElementsCount) {
        if (newElementsCount > elements.length) {
            int newCapacity = Math.max(elements.length * 2, newElementsCount);
            elements = Arrays.copyOf(elements, newCapacity);
        }
    }

    /**
     * Возвращает текущую вместимость массива.
     *
     * @return текущая вместимость массива
     */
    @Override
    public int getCapacity() {
        return elements.length;
    }

    /**
     * Инвертирует порядок элементов в массиве.
     */
    @Override
    @SuppressWarnings("unchecked")
    public void reverse() {
        for (int i = 0; i < size / 2; i++) {
            T temp = (T) elements[i];
            elements[i] = elements[size - 1 - i];
            elements[size - 1 - i] = temp;
        }
    }

    /**
     * Возвращает содержимое массива в формате '[ элемент1 элемент2 ... элементN ]' или '[ ]', если массив пустой.
     *
     * @return содержимое массива в виде строки
     */
    @Override
    public Object[] toArray() {
        return Arrays.copyOf(elements, size);
    }

    /**
     * Возвращает содержимое массива в формате '[ элемент1 элемент2 ... элементN ]' или '[ ]', если массив пустой.
     *
     * @return содержимое массива в виде строки
     */
    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[ ");
            for (int i = 0; i < size; i++) {
                sb.append(elements[i]);
                sb.append(" ");
            }
            sb.append("]");
            return sb.toString();
        }
    }

    private void checkBounds(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("Индекс за границами массива: " + index);
        }
    }
}
