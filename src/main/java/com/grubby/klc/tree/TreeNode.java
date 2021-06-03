package com.grubby.klc.tree;

/**
 * treeNode
 *
 * @author grubby
 */
public class TreeNode {

    public String value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(String value) {
        this.value = value;
    }

    public void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.println(treeNode.value);

        treeNode.preOrder(treeNode.left);
        treeNode.lastOrder(treeNode.right);
    }

    public void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        inOrder(treeNode.left);

        System.out.println(treeNode.value);
        inOrder(treeNode.right);
    }

    public void lastOrder(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }

        treeNode.lastOrder(treeNode.right);
        treeNode.lastOrder(treeNode.left);
        System.out.println(treeNode.value);
    }


}
