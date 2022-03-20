package org.tonkushin;

/**
 * Дерево
 */
public class BinaryTree {
    private Node root;

    /**
     * Вставка элемента
     *
     * @param value значение
     */
    public void insert(int value) {
        root = insert(root, value);
    }

    private Node insert(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }

        if (value < current.getValue()) {
            current.setLeft(insert(current.getLeft(), value));
        } else if (value > current.getValue()) {
            current.setRight(insert(current.getRight(), value));
        }

        return current;
    }

    @SuppressWarnings("UnusedReturnValue")
    public boolean search(int value) {
        return search(root, value);
    }

    private boolean search(Node current, int value) {
        if (current == null) {
            return false;
        }

        if (current.getValue() == value) {
            return true;
        }

        return value < current.getValue() ? search(current.getLeft(), value) : search(current.getRight(), value);
    }

    public void remove(int value) {
        remove(root, value);
    }

    private Node remove(Node current, int value) {
        if (current == null) {
            return null;
        }

        // Узел найден
        if (value == current.getValue()) {
            // Когда нет дочерних узлов
            if (current.getLeft() == null && current.getRight() == null) {
                return null;
            }

            // Когда есть один из дочерних узлов
            if (current.getRight() == null) {
                return current.getLeft();
            }

            if (current.getLeft() == null) {
                return current.getRight();
            }

            // Когда есть 2 дочерних узла
            int smallestValue = findSmallestValue(current.getRight());
            current.setValue(smallestValue);
            current.setRight(remove(current.getRight(), smallestValue));

            return current;
        }

        if (value < current.getValue()) {
            current.setLeft(remove(current.getLeft(), value));
            return current;
        }
        current.setRight(remove(current.getRight(), value));

        return current;
    }

    private int findSmallestValue(Node current) {
        return current.getLeft() == null ? current.getValue() : findSmallestValue(current.getLeft());
    }

    public void print() {
        if (root != null) {
            print(root);
        }
    }

    private void print(Node current) {
        if (current.getLeft() != null) {
            print(current.getLeft());
        }

        if (current.getRight() != null) {
            print(current.getRight());
        }

        System.out.print(current.getValue() + " ");
    }
}
