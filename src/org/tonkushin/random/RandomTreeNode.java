package org.tonkushin.random;

public class RandomTreeNode {
    int key;
    int size;
    RandomTreeNode left;
    RandomTreeNode right;

    public RandomTreeNode(int k) {
        this.key = k;
        size = 1;
    }

    public int getKey() {
        return key;
    }

    // обертка для поля size, работает с пустыми деревьями (t=NULL)
    static int getSize(RandomTreeNode p) {
        if (p == null) {
            return 0;
        }

        return p.size;
    }

    // установление корректного размера дерева
    public static void fixSize(RandomTreeNode p) {
        p.size = getSize(p.left) + getSize(p.right) + 1;
    }

    // Поиск ключа k в дереве p
    public static RandomTreeNode find(RandomTreeNode p, int k) {
        if (p == null) {
            return null;
        }

        if (k == p.key) {
            return p;
        }

        if (k < p.key) {
            return find(p.left, k);
        } else {
            return find(p.right, k);
        }
    }

    // Классическая вставка нового узла с ключом k в дерево
    static RandomTreeNode insertClassic(RandomTreeNode p, int k) {
        if (p == null) {
            return new RandomTreeNode(k);
        }

        if (p.key > k) {
            p.left = insert(p.left, k);
        } else {
            p.right = insert(p.right, k);
        }

        fixSize(p);
        return p;
    }

    // правый поворот вокруг узла p
    public static RandomTreeNode rotateRight(RandomTreeNode p) {
        RandomTreeNode q = p.left;

        if (q == null) {
            return p;
        }

        p.left = q.right;
        q.right = p;
        q.size = p.size;
        fixSize(p);

        return q;
    }

    // Левый поворот вокруг узла q
    static RandomTreeNode rotateLeft(RandomTreeNode q) {
        RandomTreeNode p = q.right;

        if (p == null) {
            return q;
        }
        q.right = p.left;
        p.left = q;
        p.size = q.size;
        fixSize(q);

        return p;
    }

    // вставка нового узла с ключом k в корень дерева p
    public static RandomTreeNode insertRoot(RandomTreeNode p, int k) {
        if (p == null) {
            return new RandomTreeNode(k);
        }

        if (k < p.key) {
            p.left = insertRoot(p.left, k);
            return rotateRight(p);
        } else {
            p.right = insertRoot(p.right, k);
            return rotateLeft(p);
        }
    }

    // рандомизированная вставка нового узла с ключом k в дерево p
    public static RandomTreeNode insert(RandomTreeNode p, int k) {
        if (p == null) {
            return new RandomTreeNode(k);
        }

        if (getRandomNumber(0, Integer.MAX_VALUE) % (p.size + 1) == 0) {
            return insertRoot(p, k);
        }

        if (p.key > k) {
            p.left = insertClassic(p.left, k);
        } else {
            p.right = insertClassic(p.right, k);
        }

        fixSize(p);

        return p;
    }

    // объединение двух деревьев
    RandomTreeNode join(RandomTreeNode p, RandomTreeNode q) {
        if (p == null) {
            return q;
        }

        if (q == null) {
            return p;
        }

        if (getRandomNumber(0, Integer.MAX_VALUE) % (p.size + q.size) < p.size) {
            p.right = join(p.right, q);
            fixSize(p);
            return p;
        } else {
            q.left = join(p, q.left);
            fixSize(q);
            return q;
        }
    }

    RandomTreeNode remove(RandomTreeNode p, int k) {
        if (p == null) {
            return null;
        }

        if (p.key == k) {
            return join(p.left, p.right);
        } else if (k < p.key) {
            p.left = remove(p.left, k);
        } else {
            p.right = remove(p.right, k);
        }

        return p;
    }

    @SuppressWarnings("SameParameterValue")
    private static int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public String toString() {
        return "RandomTreeNode{" +
                "key=" + key +
                ", size=" + size +
                '}';
    }
}
