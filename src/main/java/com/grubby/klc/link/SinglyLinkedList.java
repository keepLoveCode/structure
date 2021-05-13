package com.grubby.klc.link;


/**
 * 单向链表添加
 *
 * @author grubby
 */
public class SinglyLinkedList {

    private Node head;

    private int size;

    public void addFirst(String value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        size++;
    }

    /**
     * 核心思路，就是如何断连接，
     */
    public void invertList() {
        if (head == null || head.next == null) {
            return;
        }
        Node newHead = null;
        Node tmp = head;
        while (tmp != null) {
            Node headNode = tmp;
            tmp = tmp.next;
            headNode.next = newHead;
            newHead = headNode;
        }
        head = newHead;
    }

    /**
     * 带头翻转
     *
     * @return
     */
    public static Node invertHeadNode(Node p) {
        if (p == null || p.next == null) {
            return p;
        }
        Node head = new Node("1", null);
        head.next = p;
        Node cur = p.next;
        Node next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = head.next;
            head.next = cur;
            cur = next;
        }
        return head.next;
    }

    /**
     * 反转node
     *
     * @param node
     * @return
     */
    public static Node invertNode(Node node) {
        if (node == null || node.next == null) {
            return node;
        }
        Node pre = null;
        Node cur = node;
        //1. 这种
//        Node next = null;
//        while (cur != null) {
//            next = cur.next;
//            cur.next = pre;
//            pre = cur;
//            cur = next;
//        }
        //2.名
        while (cur != null) {
            Node tmp = cur;
            cur = cur.next;
            tmp.next = pre;
            pre = tmp;
        }
        return pre;
    }

    public void addLast(String value) {
        Node node = new Node(value, null);
        if (head == null) {
            head = node;
        } else {
            Node tail = head;
            while (tail.next != null) {
                tail = tail.next;
            }
            tail.next = node;
        }
        size++;
    }

    private String getValue(int i) {
        return get(i).value;
    }

    private Node get(int i) {
        checkPosition(i);
        Node tmp = head;
        int current = 0;
        while (tmp != null) {
            if (current == i) {
                return tmp;
            } else {
                tmp = tmp.next;
                current++;
            }
        }
        return null;
    }

    public void printAll() {
        Node tmp = head;
        while (tmp != null) {
            System.out.print("[" + tmp.value + "] ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public void remove(int index) {
        checkPosition(index);
        //1.
        if (index == 0) {
            head = head.next;
            return;
        }
        Node pre = head;
        int currentIndex = 1;
        while (pre.next != null && currentIndex++ < index) {
            pre = pre.next;
        }
        pre.next = pre.next.next;
    }

    private void checkPosition(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public static class Node {

        private Node next;

        private String value;

        public Node(String value, Node next) {
            this.next = next;
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value + " ";
        }

        public void printAll() {
            Node tmp = this;
            while (tmp != null) {
                System.out.print(tmp);
                tmp = tmp.next;
            }
            System.out.println();
        }
    }
}
