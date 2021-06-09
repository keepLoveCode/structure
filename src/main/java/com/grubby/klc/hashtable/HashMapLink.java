package com.grubby.klc.hashtable;

/**
 * 拉链法 hashTable
 *
 * @author grubby
 */
public class HashMapLink {

    private float factor = 0.75f;

    private int[] hashTable;

    private int count = 0;

    private int capacity = 16;


    public HashMapLink() {
        hashTable = new int[capacity];
        count = 0;
    }

    public void add(Integer value) {
        Node node = new Node(value);
        add(node);
    }

    private void add(Node node) {
        //查找key
        // 有相同key则更新
        // 无相同key，判断是否需要扩容
        //扩容
        // 查值
    }

    private int hashIndex(Integer value) {

    }

    private int hash(Integer value) {
        return value;
    }

    public static class Node {

        private Integer value;

        private Node next;

        public Node(Integer value) {
            this.value = value;
        }
    }
}
