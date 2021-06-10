package com.grubby.klc.hashtable;

/**
 * lruCache
 *
 * @author grubby
 */
public class LruCache {

    private float loadFactor = 0.75f;

    private int capacity = 8;

    private int cacheSize = 1024;

    private int modCount = 0;

    private int threshold;

    private Entry head;

    private Entry tail;

    private Entry[] hashTable;

    public LruCache() {
        this(8, 1024);
    }

    public LruCache(int capacity, int cacheSize) {
        this.capacity = capacity;
        this.cacheSize = cacheSize;
        this.threshold = (int) (loadFactor * capacity);
        hashTable = new Entry[capacity];
        head = new Entry(null, null);
        tail = new Entry(null, null);
        head.before = tail;
        head.after = tail;
        tail.after = head;
        tail.before = head;
    }

    public void put(Integer key, Integer value) {
        int index = indexOf(key);
        Entry entry = new Entry(key, value);
        if (hashTable[index] == null) {
            hashTable[index] = entry;
        } else {
            Entry p = hashTable[index];
            while (p != null && p.key != key) {
                p = p.next;
            }
            if (p == null) {
                p.next = entry;
            } else {
                p.value = value;
            }
        }
    }

    public Integer get(Integer key) {
        return -1;
    }

    public boolean remove(Integer key) {
        return false;
    }

    private int indexOf(Integer key) {
        return key;
    }

    private int hash(Integer key) {
        return key;
    }

    public class Entry {

        private Integer key;

        private Integer value;

        private Integer hash;

        Entry next;

        Entry before;

        Entry after;


        public Entry(Integer key, Integer value) {
            this.key = key;
            this.value = value;
            this.hash = key;
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

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

    }

}
