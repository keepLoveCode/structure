package com.grubby.klc.tree;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * treeNode
 *
 * @author grubby
 */
public class TreeNode {

    public int value;

    public TreeNode left;

    public TreeNode right;

    public TreeNode(int value) {
        this.value = value;
    }

    /**
     * 递推公式 f(n) = p(n) + f(n.left) + f(n.right)
     *
     * @param treeNode
     */
    public void preOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        System.out.println(treeNode.value);

        treeNode.preOrder(treeNode.left);
        treeNode.lastOrder(treeNode.right);
    }


    /**
     * 递推公式: f(n) = f(n.left) + p(n) + f(n.right)
     *
     * @param treeNode
     */
    public void inOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }

        inOrder(treeNode.left);

        System.out.println(treeNode.value);
        inOrder(treeNode.right);
    }

    /**
     * 递推公司
     * f(n) = f(n.left) + f(n.right) + p(n)
     *
     * @param treeNode
     */
    public void lastOrder(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }

        treeNode.lastOrder(treeNode.right);
        treeNode.lastOrder(treeNode.left);
        System.out.println(treeNode.value);
    }


    public void levelOrder(TreeNode treeNode) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(treeNode);
        while (queue.peek() != null) {
            TreeNode poll = queue.poll();
            if (poll.left != null) {
                queue.offer(poll.left);
            }

            if (poll.right != null) {
                queue.offer(poll.right);
            }

            System.out.println(poll.value);
        }
    }

    /**
     * 递推公式
     * f(n) = max(f(n.left),f(n,right)) + 1;
     *
     * @param treeNode
     * @return
     */
    public int deeplyLength(TreeNode treeNode) {
        if (treeNode == null) {
            return -1;
        }
        int leftH = deeplyLength(treeNode.left);
        int rightH = deeplyLength(treeNode.right);
        int max = leftH > rightH ? leftH : rightH;
        return ++max;
    }


}

