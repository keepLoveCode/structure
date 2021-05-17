package com.grubby.klc.link;

/**
 * @author grubby
 */
public class CycleLinkedList {

    private Node head;

    private Node tail;


    public void addFirst(String value) {
        Node node = new Node(value);
        addFirst(node);
    }

    private void addFirst(Node node) {
        if (head == null) {
            head = tail = node;
            head.next = tail;
        } else {
            node.next = head;
            head = node;
            tail.next = head;
        }
    }

    public void addLast(String value) {
        Node node = new Node(value);
        addLast(node);
    }

    private void addLast(Node node) {
        if (head == null) {
            head = tail = node;
            head.next = tail;
        } else {
            tail.next = node;
            node.next = head;
            tail = node;
        }
    }

    private class Node {

        private String value;

        private Node next;

        public Node(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public Node getNext() {
            return next;
        }

    }
}

