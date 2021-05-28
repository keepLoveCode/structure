package com.grubby.klc.sort.sum;

/**
 * @author grubby
 */
public class SortAL {

    /**
     * 未排序区
     * 冒泡区
     *
     * @param arr
     */
    public static void bubble(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int bubbleLength = arr.length - i - 1;
            boolean isBubble = false;
            for (int j = 0; j < bubbleLength; j++) {
                if (arr[j] > arr[j + 1]) {
                    int swap = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = swap;
                    isBubble = true;
                }
            }
            if (!isBubble) {
                break;
            }
        }
    }

    /**
     * 待排序
     * 已排序区
     * 最终设值
     *
     * @param arr
     */
    public static void insertion(int[] arr) {

        for (int r = 1; r < arr.length; r++) {
            int value = arr[r];
            int l = r - 1;
            for (; l >= 0; l--) {
                if (arr[l] > value) {
                    arr[l + 1] = arr[l];
                } else {
                    break;
                }
            }
            arr[l + 1] = value;
        }
    }

    /**
     * 待排序区
     * <p>
     * 选择区
     *
     * @param arr
     */
    public static void selection(int[] arr) {

        for (int i = 0; i < arr.length; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int swap = arr[min];
            arr[min] = arr[i];
            arr[i] = swap;
        }
    }


    public static void main(String[] args) {
//        int[] arr = new int[]{6, 2, 3, 2, 1, 5, 4};
//        int[] arr = new int[]{6, 2, 1, 3, 4, 5};
        int[] arr = new int[]{6};
//        insertion(arr);
        selection(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
