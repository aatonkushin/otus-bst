package org.tonkushin;

import org.tonkushin.random.RandomTree;

public class Main {

    /**
     * Максимальное кол-во элементов для дерева из элементов по возрастанию ~ 20_000
     */
    private static final int length = 1_000_000;

    private static final Stopwatch sw = new Stopwatch();

    public static void main(String[] args) {
        for (int n = 800_000; n <= 1_000_000; n += 50_000) {
            // Создаём деревья
            BinaryTree tree = new BinaryTree();
            RandomTree bambooRandomTree = new RandomTree();
            RandomTree randomTree = new RandomTree();

            generate(tree, bambooRandomTree, randomTree);

            insert(n, tree, bambooRandomTree, randomTree);

            // Создаём массив элементов для поиска и удаления
            int[] testItems = new int[n / 10];
            for (int i = 0; i < testItems.length; i++) {
                testItems[i] = getRandomNumber(0, length);
            }

            search(n, tree, bambooRandomTree, randomTree, testItems);

            remove(n, tree, bambooRandomTree, randomTree, testItems);

            System.out.println("=== === === === === === === === === === === === ===");
            System.out.println();
        }
    }

    private static void generate(BinaryTree tree, RandomTree bambooRandomTree, RandomTree randomTree) {
        // Простое дерево - случайная вставка
        System.out.print("Generating tree with length " + length + ": ");
        sw.start();
        for (int i = 0; i < length; i++) {
            int number = getRandomNumber(0, length);
            tree.insert(number);
        }
        sw.stop();
        System.out.println(sw);

        // Рандомизированное дерево - последовательная вставка
        System.out.print("Generating bambooRandomTree with length " + length + ": ");
        sw.start();
        for (int i = 0; i < length; i++) {
            bambooRandomTree.insert(i);
        }
        sw.stop();
        System.out.println(sw);

        // Рандомизированное дерево - случайная вставка
        System.out.print("Generating randomTree with length " + length + ": ");
        sw.start();
        for (int i = 0; i < length; i++) {
            int number = getRandomNumber(0, length);
            randomTree.insert(number);
        }
        sw.stop();
        System.out.println(sw);

        System.out.println("=== === ===");
    }

    private static void insert(int n, BinaryTree tree, RandomTree bambooRandomTree, RandomTree randomTree) {
        // Простое дерево, созданное случайной вставкой
        System.out.printf("Insert %d elements in tree: ", n);
        sw.start();
        for (int i = 0; i < n; i++) {
            tree.insert(getRandomNumber(0, length + n));
        }
        sw.stop();
        System.out.println(sw);

        // Рандомизированное дерево, созданное последовательной вставкой
        System.out.printf("Insert %d elements in bambooRandomTree: ", n);
        sw.start();
        for (int i = 0; i < n; i++) {
            bambooRandomTree.insert(getRandomNumber(0, length + n));
        }
        sw.stop();
        System.out.println(sw);

        // Рандомизированное дерево, созданное случайной вставкой
        System.out.printf("Insert %d elements in randomTree: ", n);
        sw.start();
        for (int i = 0; i < n; i++) {
            randomTree.insert(getRandomNumber(0, length + n));
        }
        sw.stop();
        System.out.println(sw);

        System.out.println("=== === ===");
    }

    private static void search(int n, BinaryTree tree, RandomTree bambooRandomTree, RandomTree randomTree, int[] testItems) {
        // Поиск для дерева из случайных элементов
        System.out.print("Search " + n / 10 + " items in tree: ");
        sw.start();
        for (int testItem : testItems) {
            tree.search(testItem);
        }
        sw.stop();
        System.out.println(sw);


        // Поиск для рандомизированного дерева из элементов по возрастанию
        System.out.print("Search " + n / 10 + " items in bambooRandomTree tree: ");
        sw.start();
        for (int testItem : testItems) {
            bambooRandomTree.search(testItem);
        }
        sw.stop();
        System.out.println(sw);

        // Поиск для рандомизированного дерева из случайных элементов
        System.out.print("Search " + n / 10 + " items in randomTree: ");
        sw.start();
        for (int testItem : testItems) {
            randomTree.search(testItem);
        }
        sw.stop();
        System.out.println(sw);

        System.out.println("=== === ===");
    }

    private static void remove(int n, BinaryTree tree, RandomTree bambooRandomTree, RandomTree randomTree, int[] testItems) {
        // Удаление для дерева из случайных элементов
        System.out.print("Remove " + n / 10 + " items from tree: ");
        sw.start();
        for (int testItem : testItems) {
            tree.remove(testItem);
        }
        sw.stop();
        System.out.println(sw);

        // Удаление для рандомизированного дерева из элементов по возрастанию
        System.out.print("Remove " + n / 10 + " items from bambooRandomTree: ");
        sw.start();
        for (int testItem : testItems) {
            bambooRandomTree.remove(testItem);
        }
        sw.stop();
        System.out.println(sw);

        // Удаление для рандомизированного дерева из случайных элементов
        System.out.print("Remove " + n / 10 + " items from randomTree: ");
        sw.start();
        for (int testItem : testItems) {
            randomTree.remove(testItem);
        }
        sw.stop();
        System.out.println(sw);
    }

    @SuppressWarnings("SameParameterValue")
    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
}
