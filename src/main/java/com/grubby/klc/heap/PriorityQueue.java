package com.grubby.klc.heap;

/**
 * 大顶堆
 *
 * @author grubby
 */
public class PriorityQueue {

    private int[] arr;

    private int count;

    private int capacity;

    public PriorityQueue(int capacity) {
        this.capacity = capacity;
        this.arr = new int[capacity + 1];
    }

    public void offer(int value) {
        if (count >= capacity) {
            return;
        }
        add(value);
    }

    private void add(int value) {
        arr[++count] = value;
        int i = count;
        while (i / 2 > 0 && arr[i / 2] < arr[i]) {
            swap(arr, i, i / 2);
            i = i / 2;
        }
    }

    private void swap(int arr[], int first, int second) {
        int swap = arr[first];
        arr[first] = arr[second];
        arr[second] = swap;
    }

    public int take() {
        if (count == 0) {
            return -1;
        }
        int value = arr[1];
        arr[1] = arr[count--];
        heapify(arr, 1, count);
        return value;
    }

    private void heapify(int arr[], int start, int end) {
        while (true) {
            int maxPos = start;
            if (2 * start < end && arr[maxPos] < arr[2 * start]) {
                maxPos = 2 * start;
            }

            if (2 * start + 1 < end && arr[maxPos] < arr[2 * start + 1]) {
                maxPos = 2 * start + 1;
            }

            if (maxPos == start) {
                break;
            }

            swap(arr, start, maxPos);

            start = maxPos;
        }
    }
}
