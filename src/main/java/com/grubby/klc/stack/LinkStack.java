package com.grubby.klc.stack;

/**
 * @author grubby
 * @date 2021/5/23 20:47
 */
public class LinkStack<T> {

    private Node<T> head;

    public void push(T value) {
        Node<T> newHead = new Node<>(value);
        newHead.next = head;
        head = newHead;
    }

    public T pop() {
        if (head == null) {
            return null;
        }
        T value = head.value;
        head = head.next;
        return value;
    }

    private class Node<T> {
        private T value;

        private Node<T> next;

        public Node(T value) {
            this.value = value;
        }
    }
}
