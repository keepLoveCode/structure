package com.grubby.klc.link;

/**
 * 再写一次
 *
 * @author grubby
 */
public class SinglyLinkList {

    private Node header;

    public void addLast(String value) {
        Node node = new Node(value);
        addLast(node);
    }


    private void addLast(Node node) {
        if (header == null) {
            header = node;
            return;
        }

        Node tail = header;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    private void addFirst(String value) {
        Node node = new Node(value, null);
        if (header == null) {
            header = node;
        } else {
            node.next = header;
            header = node;
        }
    }

    public int findValueIndex(String value) {
        Node curr = header;
        int i = 0;
        while (curr != null) {
            if (curr.getValue().equals(value)) {
                return i;
            }
            curr = curr.next;
            i++;
        }
        return -1;
    }

    public String findByIndex(int index) {
        Node curr = header;

        int pos = 0;

        while (curr != null) {
            if (pos == index) {
                return curr.getValue();
            } else {
                curr = curr.next;
                pos++;
            }
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private class Node {

        String value;

        Node next;

        Node(String value) {
            this(value, null);
        }

        Node(String value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

        public String getValue() {
            return value;
        }
    }

}

