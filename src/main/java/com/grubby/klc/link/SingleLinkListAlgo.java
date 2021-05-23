package com.grubby.klc.link;

/**
 * 算法题
 * 0.单链表反转
 * 1.找中间节点
 * 2.删除第k个节点
 * 3.两个有序的链表合并
 * 4.链表中环的检测
 * 5.找环的入口
 * 6.求环的周长
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
     * (x-1)+k = (n-1)
     * 但是 k = n 时 x= 0 没有第0个前驱节点 所以要一个哨兵
     * x +k = n;
     * <p>
     * fast k = n -x;
     * <p>
     * <p>
     * 有一个很重要的问题
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
        Node head = new Node("");
        head.next = node;
        Node p = head;
        while (p != null && count < k) {
            p = p.next;
            count++;
        }

        if (p == null) {
            return node;
        }

        Node slow = head;
        Node fast = p;

        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        slow.next = slow.next.next;
        return head.next;
    }

    /**
     * 检查是否存在环
     * 思路：
     * 快慢指针
     * 1. 如果存在环，快指针与慢指针的相对速度是1 入环时 快指针只需要走 0<x<c(周长) 则能追上慢指针。追击问题
     * <p>
     * 2.线性同余  a*x 恒= b (mod c) 去 a c最大公约数 gcd(a,c) 只能整除b是则有解  b /gcd(a,c)
     * 设 A-B 在环中一定能相遇
     * A 第一次进入环时 距入口为x1
     * B 此时距入口为x2
     * AB 移动n步后相遇
     * C 为环的周长
     * 择有
     * <p>
     * x1 + Va* n (mod C) 恒= x2 + Vb* n (mod C)
     * 同余式左右两边是可以用四则运算的，不影响结果 则
     * <p>
     * (Vb-Va)*n 恒= (x1-x2) (mod C)
     * 则求 (x1-x2) / gcd((Vb-Va),C)
     * 在不确定x1-x2时，即 非环部分长度。 Vb-Va =1 最大公约数为1时，永远有解
     * 所以要保证存在环时能相遇 需要设计Vb-Va =1即可
     *
     * @return
     */
    public static boolean checkCircle(Node node) {
        Node slow = node;
        Node fast = node;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }

    /**
     * 解题思路
     * 非环长 L
     * B以1步速走L步时则是环的入口
     * 求L
     * <p>
     * S为步数
     * S = L + x (慢指针一步，第一圈就相遇了 )
     * 2S = L + k*C + x (快指针)
     * 2L + 2x = L + K*C + x
     * L = K*c - x;
     * L = (K-1)c + c-x
     * 当第一次相遇之后 A离入口为 c-x
     * <p>
     * 所以
     * 当移动L步之后， A必然在入口
     * 使用一个指针B从开始出移动L步， 此时A移动((K-1)c + c-x) 与 B相遇
     *
     * @param node
     * @return
     */
    public static Node findEntryOfCircle(Node node) {
        Node slow = node;
        Node fast = node;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                fast = node;
                while (slow != fast) {
                    fast = fast.next;
                    slow = slow.next;
                }
                return slow;
            }
        }
        return null;
    }

    public static int getCircleLength(Node node) {
        Node slow = node;
        Node f = node;
        while (f != null && f.next != null) {
            slow = slow.next;
            f = f.next.next;
            if (slow == f) {
                break;
            }
        }
        if (f == null || f.next == null) {
            return 0;
        }

        slow = slow.next;
        f = f.next.next;
        int circleLength = 1;
        while (slow != f) {
            slow = slow.next;
            f = f.next.next;
            circleLength++;
        }
        return circleLength;
    }

    private static class Node {
        public String value;

        public Node next;

        public Node(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    public static void main(String[] args) {
//        testCheckCircle();
        testFindEntryOfCircle();
    }


    private static void testRemoveLastKth() {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        Node node4 = new Node("D");
        Node node5 = new Node("E");
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        Node node = SingleLinkListAlgo.removeLastKth(node1, 1);
        System.out.println(node);
    }

    private static void testFindEntryOfCircle() {
        Node circleNode = getCircleNode();
        Node entryOfCircle = findEntryOfCircle(circleNode);
        System.out.println(entryOfCircle);
    }


    private static void testCheckCircle() {
        boolean exists = checkCircle(getCircleNode());
        System.out.println(exists);
    }

    private static Node getCircleNode() {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        Node node4 = new Node("D");
        Node node5 = new Node("E");
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node1;
        return node1;
    }

    private static Node createNode() {
        Node node1 = new Node("A");
        Node node2 = new Node("B");
        Node node3 = new Node("C");
        Node node4 = new Node("D");
        Node node5 = new Node("E");
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        return node1;
    }
}
