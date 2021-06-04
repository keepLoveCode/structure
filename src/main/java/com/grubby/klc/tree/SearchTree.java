package com.grubby.klc.tree;

public class SearchTree {

    private TreeNode root;

    public TreeNode searchNode(int value) {
        TreeNode parent = root;
        while (parent != null) {
            if (parent.value < value) {
                parent = parent.left;
            } else if (parent.value > value) {
                parent = parent.right;
            } else {
                return parent;
            }
        }
        return null;
    }

    public void addNode(int value) {
        TreeNode treeNode = new TreeNode(value);
        TreeNode head = root;
        if (head == null) {
            head = treeNode;
        }

        TreeNode p = root;

        while (p != null) {

        }

    }
}
