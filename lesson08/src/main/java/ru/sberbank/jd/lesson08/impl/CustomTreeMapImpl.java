package ru.sberbank.jd.lesson08.impl;

import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import ru.sberbank.jd.lesson08.CustomTreeMap;

/**
 * Реализация интерфейса {@link CustomTreeMap} с использованием двоичного дерева поиска.
 */
public class CustomTreeMapImpl<K, V> implements CustomTreeMap<K, V> {

    private Node<K, V> root;
    private final Comparator<K> comparator;
    private int size;


    /**
     * Создает новую пустую CustomTreeMap с заданным компаратором ключей.
     *
     * @param comparator компаратор ключей
     */
    public CustomTreeMapImpl(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    /**
     * Внутренний класс, представляющий узел дерева.
     */
    private static class Node<K, V> {
        private K key;
        private V value;
        private Node<K, V> left;
        private Node<K, V> right;

        /**
         * Конструктор узла с заданным ключом и значением.
         *
         * @param key   ключ узла
         * @param value значение узла
         */
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V get(K key) {
        Node<K, V> node = findNode(key);
        return (node != null) ? node.value : null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }
        if (root == null) {
            root = new Node<>(key, value);
            size++;
            return null;
        }

        Node<K, V> parent = null;
        Node<K, V> current = root;
        int compareResult;

        while (current != null) {
            compareResult = comparator.compare(key, current.key);
            parent = current;

            if (compareResult < 0) {
                current = current.left;
            } else if (compareResult > 0) {
                current = current.right;
            } else {
                V oldValue = current.value;
                current.value = value;
                return oldValue;
            }
        }

        Node<K, V> newNode = new Node<>(key, value);
        compareResult = comparator.compare(key, parent.key);

        if (compareResult < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }

        size++;
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key cannot be null");
        }

        Node<K, V> parent = null;
        Node<K, V> current = root;

        while (current != null) {
            int compareResult = comparator.compare(key, current.key);

            if (compareResult == 0) {
                V removedValue = current.value;

                if (current.left == null && current.right == null) {
                    if (parent == null) {
                        root = null;
                    } else if (current == parent.left) {
                        parent.left = null;
                    } else {
                        parent.right = null;
                    }
                } else if (current.left != null && current.right != null) {
                    Node<K, V> successorParent = current;
                    Node<K, V> successor = current.right;

                    while (successor.left != null) {
                        successorParent = successor;
                        successor = successor.left;
                    }

                    current.key = successor.key;
                    current.value = successor.value;

                    if (successorParent.left == successor) {
                        successorParent.left = successor.right;
                    } else {
                        successorParent.right = successor.right;
                    }
                } else {
                    Node<K, V> child = (current.left != null) ? current.left : current.right;

                    if (parent == null) {
                        root = child;
                    } else if (current == parent.left) {
                        parent.left = child;
                    } else {
                        parent.right = child;
                    }
                }

                size--;
                return removedValue;
            } else if (compareResult < 0) {
                parent = current;
                current = current.left;
            } else {
                parent = current;
                current = current.right;
            }
        }

        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsKey(K key) {
        return findNode(key) != null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean containsValue(V value) {
        Deque<Node<K, V>> stack = new ArrayDeque<>();
        Node<K, V> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();

            if (current.value.equals(value)) {
                return true;
            }

            current = current.right;
        }

        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] keys() {
        Object[] keys = new Object[size];
        int index = 0;
        Deque<Node<K, V>> stack = new ArrayDeque<>();
        Node<K, V> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            keys[index++] = current.key;
            current = current.right;
        }

        return keys;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object[] values() {
        Object[] values = new Object[size];
        int index = 0;
        Deque<Node<K, V>> stack = new ArrayDeque<>();
        Node<K, V> current = root;

        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            values[index++] = current.value;
            current = current.right;
        }

        return values;
    }

    /**
     * Возвращает узел дерева по заданному ключу.
     *
     * @param key ключ для поиска
     * @return узел дерева с указанным ключом или {@code null}, если такого ключа нет
     */
    private Node<K, V> findNode(K key) {
        Node<K, V> current = root;

        while (current != null) {
            int compareResult = comparator.compare(key, current.key);

            if (compareResult < 0) {
                current = current.left;
            } else if (compareResult > 0) {
                current = current.right;
            } else {
                return current;
            }
        }
        return null;
    }
}
