package com.grubby.klc.hashtable;

import java.util.Objects;

/**
 * 拉链法 hashTable
 *
 * @author grubby
 */
public class HashMapLink {

    private float factor = 1f;

    private Node[] hashTable;

    private int size = 0;

    private int capacity = 16;

    private int threshold = 0;

    public HashMapLink() {
        hashTable = new Node[capacity];
        size = 0;
        threshold = (int) (capacity * factor);
    }

    public HashMapLink(int capacity) {
        this.capacity = capacity;
        hashTable = new Node[capacity];
        size = 0;
        threshold = (int) (capacity * factor);
    }

    public void put(Integer key, Integer value) {
        Node node = new Node(key, value);
        put(node);
    }


    public int get(Integer key) {
        Node node = new Node(key, null);
        Node p = hashTable[indexOf(node)];
        while (p != null) {
            if (p.key.equals(key)) {
                return p.value;
            } else {
                p = p.next;
            }
        }
        return -1;
    }

    private void put(Node node) {
        //查找key
        Node existsNode = searchNode(node);
        if (Objects.isNull(existsNode)) {
            insert(node);
        } else {
            existsNode.setValue(node.value);
        }
    }

    private void insert(Node node) {
        //插入
        int index = indexOf(node);
        if (hashTable[index] == null) {
            hashTable[index] = node;
        } else {
            Node head = hashTable[index];
            while (head.next != null) {
                head = head.next;
            }
            head.next = node;
        }
        //先插入在扩容
        if (++size > threshold) {
            resize();
        }
    }

    private void resize() {
        capacity = (capacity << 1);
        threshold = (int) (capacity * factor);
        Node[] newTable = new Node[capacity];
        for (Node node : hashTable) {
            Node p = node;
            while (p != null) {
                int index = indexOf(p);
                if (newTable[index] == null) {
                    newTable[index] = p;
                } else {
                    Node head = newTable[index];
                    while (head.next != null) {
                        head = head.next;
                    }
                    head.next = null;
                }
                Node swap = p;
                p = p.next;
                swap.next = null;
            }
        }
        hashTable = newTable;
    }

    private Node searchNode(Node node) {
        int index = indexOf(node);
        if (hashTable[index] != null) {
            Node head = hashTable[index];
            while (head != null) {
                if (head.equals(node)) {
                    return head;
                }
                head = head.next;
            }
        }
        return null;
    }


    private int indexOf(Node node) {
        return node.hash & (capacity - 1);
    }

    public static class Node {

        private Integer key;

        private Integer value;

        private int hash;

        private Node next;

        public Node(Integer key, Integer value) {
            this.value = value;
            this.key = key;
            this.hash = key;
        }

        @Override
        public boolean equals(Object object) {
            if (this == object) return true;
            if (object == null || getClass() != object.getClass()) return false;
            Node node = (Node) object;
            return Objects.equals(key, node.key);
        }

        @Override
        public int hashCode() {
            return hash;
        }

        public Integer getKey() {
            return key;
        }

        public void setKey(Integer key) {
            this.key = key;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }
    }

    public static void main(String[] args) {
        HashMapLink hashMapLink = new HashMapLink(8);
        hashMapLink.put(1,1);
        hashMapLink.put(2,2);
        hashMapLink.put(3,3);
        hashMapLink.put(4,4);
        hashMapLink.put(8,8);
        hashMapLink.put(16,16);
        hashMapLink.put(9,9);
        hashMapLink.put(10,10);
        hashMapLink.put(11,11);
        hashMapLink.put(12,12);
        hashMapLink.put(13,13);
        hashMapLink.put(17,17);
        int i = hashMapLink.get(17);
        System.out.println(i);
    }
}
