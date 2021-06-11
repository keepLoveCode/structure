package com.grubby.klc.hashtable;

import java.util.Objects;

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
        Entry entry = getEntry(key);
        if (entry != null) {
            entry.value = value;
        } else {
            entry = new Entry(key, value);
            addEntry(entry);
            Entry before = tail.before;
            entry.before = before;
            entry.after = tail;
            before.after = entry;
            tail.before = entry;

            if (modCount > cacheSize) {
                cleanLast();
            }
        }
    }

    public Integer get(Integer key) {
        Entry entry = getEntry(key);
        if (Objects.isNull(entry)) {
            return -1;
        }
        entry.before.after = entry.after;
        entry.after.before = entry.before;
        head.after.before = entry;
        entry.after = head.after;
        entry.before = head;
        head.after = entry;
        return entry.value;
    }

    private void addEntry(Entry entry) {
        int index = indexOf(entry.key);
        if (hashTable[index] == null) {
            hashTable[index] = entry;
        } else {
            Entry p = hashTable[index];
            while (p.next != null) {
                p = p.next;
            }
            p.next = entry;
        }

        if (++modCount > threshold) {
            resize();
        }
    }

    private boolean deleteEntry(Integer key) {
        int index = indexOf(key);
        Entry p = hashTable[index];
        Entry pre = null;
        while (p != null && p.key != key) {
            pre = p;
            p = p.next;
        }
        if (p == null) {
            return false;
        }

        if (pre == null) {
            hashTable[index] = p.next;
        } else {
            pre.next = p.next;
        }
        modCount--;
        return true;
    }

    private void resize() {

    }

    private void cleanLast() {
        if (modCount == 0) {
            return;
        }
        Entry removeNode = tail.before;
        deleteEntry(removeNode.key);
        removeNode.before.after = tail;
        tail.before = removeNode.before;
    }

    private Entry getEntry(Integer key) {
        int index = indexOf(key);
        Entry p = hashTable[index];
        while (p != null && p.key != key) {
            p = p.next;
        }
        return p;
    }


    private int indexOf(Integer key) {
        return hash(key) & (capacity - 1);
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
