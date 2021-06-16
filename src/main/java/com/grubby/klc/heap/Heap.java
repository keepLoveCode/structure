package com.grubby.klc.heap;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 堆排序
 *
 * @author grubby
 */
public class Heap {

    private int[] arr;

    private int capacity;

    private int count;

    public Heap() {
    }

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

        arr[1] = arr[count];
        count--;
        heapify(arr, count, 1);
    }

    private void heapify(int[] arr, int count, int start) {
        while (true) {
            int maxPos = start;

            if (2 * start <= count && arr[maxPos] < arr[2 * start]) {
                maxPos = 2 * start;
            }

            if (2 * start + 1 <= count && arr[maxPos] < arr[2 * start + 1]) {
                maxPos = 2 * start + 1;
            }

            if (maxPos == start) break;

            swap(arr, start, maxPos);

            start = maxPos;
        }
    }


    private void swap(int[] arr, int first, int second) {
        int swap = arr[first];
        arr[first] = arr[second];
        arr[second] = swap;
    }


    public void printHeap() {
        if (count == 0) {
            System.out.println("empty");
            return;
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        while (queue.peek() != null) {
            Queue<Integer> subQueue = new LinkedList<>();
            while (queue.peek() != null) {
                Integer index = queue.poll();
                System.out.print(" " + arr[index]);
                subQueue.offer(index);

            }

            while (subQueue.peek() != null) {
                Integer poll = subQueue.poll();
                if (2 * poll <= count) {
                    queue.offer(2 * poll);
                }

                if (2 * poll + 1 <= count) {
                    queue.offer(2 * poll + 1);
                }
            }
            System.out.println();
        }
    }

    /**
     * 自底向上
     *
     * @param a
     * @param n
     */
    public void buildHeap(int[] a, int n) {
        arr = a;
        capacity = n;
        count = 0;
        for (int i = 1; i <= n; i++) {
            this.insert(a[i]);
        }
    }


    /**
     * 自顶向下
     *
     * @param a
     * @param n
     */
    public void buildHeap1(int[] a, int n) {
        arr = a;
        capacity = n;
        count = n;
        for (int i = n / 2; i >= 1; i--) {
            heapify(arr, n, i);
        }
    }

    public void sort() {
        if (count == 0) {
            return;
        }

        while (count > 0) {
            swap(arr, 1, count);
            count--;
            heapify(arr, count, 1);
        }
    }


    public static void main(String[] args) {
//        Heap heap = new Heap(16);
//        for (int i = 1; i <= 15; i++) {
//            heap.insert(i);
//        }
//
//        heap.removeMax();
        Heap heap = new Heap();
        int[] arr = new int[16];
        for (int i = 1; i < 16; i++) {
            arr[i] = i;
        }
        heap.buildHeap1(arr, 15);
        heap.printHeap();

        heap.sort();
        heap.printHeap();
    }
}
