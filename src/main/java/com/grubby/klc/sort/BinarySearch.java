package com.grubby.klc.sort;

/**
 * 有序数组中
 * 折半思路找到那个值
 *
 * @author grubby
 * @date 2021/5/30 20:34
 */
public class BinarySearch {

    public static int search(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (arr[mid] == key) {
                return mid;
            }
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{0};
        int search = search(arr, 1);
        System.out.println(search);
    }
}
