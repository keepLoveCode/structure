package com.grubby.klc.heap;

/**
 * 堆排序
 *
 * @author grubby
 */
public class Heap {

    private int[] arr;

    private int capacity;

    private int count;

    public Heap(int capacity) {
        this.arr = new int[capacity + 1];
        this.capacity = capacity;
        this.count = 0;
    }

    public void insert(int data) {
        if (count >= capacity) {
            return;
        }

        count++;

        arr[count] = data;
        int i = count;
        while (i / 2 > 0 && arr[i] > arr[i / 2]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
    }

    public void removeMax() {
        if (count == 0) {
            return;
        }


    }

    private void swap(int[] arr, int first, int second) {
        int swap = arr[first];
        arr[first] = arr[second];
        arr[second] = swap;
    }

}
