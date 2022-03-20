package org.tonkushin;

public class Main {

    /**
     * Максимальное кол-во элементов для дерева из элементов по возрастанию ~ 20_000
     */
    private static final int length = 20_000_000;

    private static final Stopwatch sw = new Stopwatch();

    public static void main(String[] args) {
        // Создаём деревья
        BinaryTree bamboo = new BinaryTree();
        BinaryTree tree = new BinaryTree();

        System.out.print("Generating trees with length " + length + ": ");
        sw.start();
        for (int i = 0; i < length; i++) {
            int number = getRandomNumber(0, length);
            bamboo.insert(number);
            tree.insert(number);
        }
        sw.stop();
        System.out.println(sw);

        int n = 9000000;
        // Добавление n элементов
        System.out.printf("Insert %d elements in bamboo tree: ", n);
        sw.start();
        for (int i = length / 2; i < ((length / 2) + n); i++) {
            bamboo.insert(i);
        }
        sw.stop();
        System.out.println(sw);

        System.out.printf("Insert %d elements in random tree: ", n);
        sw.start();
        for (int i = 0; i < n; i++) {
            tree.insert(getRandomNumber(0, length + n));
        }
        sw.stop();
        System.out.println(sw);

        // Создаём массив элементов для поиска и удаления
        int[] testItems = new int[n / 10];
        for (int i = 0; i < testItems.length; i++) {
            testItems[i] = getRandomNumber(0, length);
        }

        // Поиск для дерева из элементов по возрастанию
        System.out.print("Search " + n / 10 + " items in bamboo tree: ");
        sw.start();
        for (int testItem : testItems) {
            bamboo.search(testItem);
        }
        sw.stop();
        System.out.println(sw);

        // Поиск для дерева из случайных элементов
        System.out.print("Search " + n / 10 + " items in random tree: ");
        sw.start();
        for (int testItem : testItems) {
            tree.search(testItem);
        }
        sw.stop();
        System.out.println(sw);

        // Удаление для дерева из элементов по возрастанию
        System.out.print("Remove " + n / 10 + " items from bamboo tree: ");
        sw.start();
        for (int testItem : testItems) {
            bamboo.remove(testItem);
        }
        sw.stop();
        System.out.println(sw);

        // Удаление для дерева из случайных элементов
        System.out.print("Remove " + n / 10 + " items from random tree: ");
        sw.start();
        for (int testItem : testItems) {
            tree.remove(testItem);
        }
        sw.stop();
        System.out.println(sw);
    }

    @SuppressWarnings("SameParameterValue")
    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
