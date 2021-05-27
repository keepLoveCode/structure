package com.grubby.klc.sort;

/**
 * 排序算法
 *
 * @author grubby
 * @date 2021/5/27 20:58
 */
public class SortAL {

    /**
     * 冒泡排序
     * 一个泡一个泡往后冒，冒到最后就是最小或大的那个
     * 起点：0
     * 终点: n -1 只计算 n-1个， 因为第一个不用冒
     * <p>
     * 冒泡的个数是 1 + 2 +.. + n-1 = O(n^2) 时间复杂度度
     * 空间复杂度O(1)
     * 原地排序
     * <p>
     * 稳定排序
     *
     * @param arr
     */
    public static void bubbleSort(int[] arr) {
        int swap;
        int n = arr.length - 1;
        for (int i = 0; i < n; i++) {
            int j = n - i;
            boolean entry = false;
            for (int k = 0; k < j; k++) {
                if (arr[k] > arr[k + 1]) {
                    swap = arr[k + 1];
                    arr[k + 1] = arr[k];
                    arr[k] = swap;
                    entry = true;
                }
            }
            if (!entry) {
                break;
            }
        }
    }

    /**
     * 插入排序
     * 思路:
     * 已排序和为排序去，每次拿未排序区第一个 与已排序去比较 让其有序
     * <p>
     * 起点: index 1
     * 终点: index n
     * 赋值操作:因为涉及到头插，到头节点移位，下一步进不了循环。所以value的赋值操作放在外面
     * <p>
     * 原地排序
     * 稳定排序
     * <p>
     * 时间复杂度 1+..+n-1
     *
     * @param arr
     */
    public static void insertSort(int[] arr) {
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
            //注意这段代码！！如果将其放在else中，当value要插入到头部时，l=0时往后移位， 下一步就进不了循环了 所以要提到外面
            arr[l + 1] = value;
        }
    }

    /**
     * 选择排序
     * 思路：
     * 分为已排序区和未排序区
     * 为排序区从0开始
     * start:0
     * end: n-1最后一个不用排
     * 纪录数组下标即可
     * @param arr
     */
    public static void selectionSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[min] > arr[j]) {
                    min = j;
                }
            }
            int swap = arr[i];
            arr[i] = arr[min];
            arr[min] = swap;
        }
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{6,2,3,2,1,5,4};
//        int[] arr = new int[]{6, 2, 1, 3, 4, 5};
        int[] arr = new int[]{2, 1};
//        bubbleSort(arr);
//        insertSort(arr);
        selectionSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }
}
