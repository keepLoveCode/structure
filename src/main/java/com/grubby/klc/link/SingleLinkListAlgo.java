package com.grubby.klc.link;

/**
 * 算法题
 * 0.单链表反转
 * 1.找中间节点
 * 2.删除第k个节点
 * 3.两个有序的链表合并
 * 4.链表中环的检测
 *
 * @author grubby
 */
public class SingleLinkListAlgo {


    /**
     * 单链表反转
     * <p>
     * f(n) = f(n.next) + head;
     *
     * @param head
     * @return
     */
    public static Node recursion(Node head) {
        if (head == null || head.next == null) {
            return head;
        }
        Node swap = head.next;
        Node newHead = recursion(swap);
        swap.next = head;
        head.next = null;
        return newHead;

    }

    /**
     * 单链表反转 左右反转
     *
     * @param head
     * @return
     */
    public static Node lfReverse(Node head) {
        Node left = null;
        Node right = head;
        Node swap = null;

        while (right != null) {
            swap = right;
            right = right.next;
            swap.next = left;
            left = swap;
        }
        return left;
    }

    /**
     * 就地反转
     *
     * @param head
     * @return
     */
    public static Node reverse(Node head) {
        if (head == null || head.next == null) {
            return null;
        }

        Node pre = head;
        Node next = head.next;
        while (next != null) {
            head.next = next.next;
            next.next = pre;
            pre = next;
            next = head.next;
        }
        return pre;
    }

    /**
     * 找中间节点
     *
     * @param node
     * @return
     */
    public static Node findMid(Node node) {
        if (node == null) {
            return null;
        }

        Node slow = node;
        Node fast = node;

        // 这里考虑的是 但1 比 2的关系不满足时，是否应该进入  满足时奇数有中间节点，进入  不满足时，没有中间节点偶数 不进入，则 slow落在前一节点

        // 也可以 p!null && p.next!=null  满足1.2时奇数,进入， 不满足 偶数 进入 slow在后一节点 未偶数时，后一节点也可以时中间节点。
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }


    /**
     * 删除倒数第k个节点
     * <p>
     * 首先移动快指针k-1，到第k个节点
     * <p>
     * 然后 慢指针从第一个节点开始移动。
     * <p>
     * x +k = n;
     * <p>
     * fast k = n -x;
     * <p>
     * slow
     *
     * @param node
     * @param k
     * @return
     */
    public static Node removeLastKth(Node node, int k) {
        if (node == null || k < 1) {
            return node;
        }

        int count = 0;
        Node p = node;

        while (p != null && count < k) {
            p = p.next;
            count++;
        }

        if (p == null) {
            return node;
        }

        Node slow = node;
        Node fast = p;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

    private static class Node {
        public String value;

        public Node next;

        public Node(String value) {
            this.value = value;
        }
    }
}
