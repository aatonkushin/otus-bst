package org.tonkushin.random;

public class RandomTree {
    RandomTreeNode root;

    public void insert(int k) {
        if (root == null) {
            root = new RandomTreeNode(k);
            return;
        }

        root = RandomTreeNode.insert(root, k);
    }

    public void remove(int k) {
        root.remove(root, k);
    }

    public RandomTreeNode search(int k) {
        return RandomTreeNode.find(root, k);
    }

    public int size(){
        return root.size;
    }

    @Override
    public String toString() {
        return "RandomTree {" +
                "root=" + root +
                '}';
    }
}
