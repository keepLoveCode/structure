package com.grubby.klc.sort;

import java.util.HashMap;
import java.util.Map;

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
     *
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


    /**
     * 时间复杂度计算
     * T(n) = 2(T(n/2)) +n
     * =2*(2*(T(n/4)) + n/2) +n
     * =4T(n/4) + 2n
     * =4(2*T(n/8) + n/4) + 2n
     * =8T(n/8) + 3n
     * = 2^kT(n/2^K) + kn
     * n/2^k = 1;
     * k = log2n
     * nC+ n*log2n = O(nlogn)
     * <p>
     * <p>
     * 空间复杂读计算。 最大空间占用未n 而且是串行，所有空间复制度未o(n)
     *
     * @param arr
     */
    public static void mergeSort(int[] arr) {
        sefMergeSort(arr, 0, arr.length - 1);
    }

    public static void sefMergeSort(int[] arr, int first, int last) {

        //等于1
        if (first >= last) {
            return;
        }

        int mid = first + (last - first) / 2;

        //首先拆分
        sefMergeSort(arr, first, mid);
        sefMergeSort(arr, mid + 1, last);

        merge(arr, first, mid, last);
    }

    public static void merge(int[] arr, int first, int mid, int last) {
        if (first >= last) {
            return;
        }
        int[] temp = new int[last - first + 1];
        int left = first;
        int right = mid + 1;

        int i = 0;
        while (left <= mid && right <= last) {
            if (arr[left] < arr[right]) {
                temp[i++] = arr[left++];
            } else {
                temp[i++] = arr[right++];
            }
        }

        while (left <= mid) {
            temp[i++] = arr[left++];
        }

        while (right <= last) {
            temp[i++] = arr[right++];
        }

        for (int j = 0; j < temp.length; j++) {
            arr[first++] = temp[j];
        }
    }

    /**
     * 快排：
     * 思路:不断的分有序区，直到最小分区完全有序
     *
     * @param arr
     */
    public static void quickSort(int[] arr) {
        sefQuickSort(arr, 0, arr.length - 1);
    }

    public static void sefQuickSort(int[] arr, int first, int last) {
        if (first >= last) {
            return;
        }
        int partition = partition(arr, first, last);
        sefQuickSort(arr, first, partition - 1);
        sefQuickSort(arr, partition + 1, last);
    }

    /**
     * 选择一个值 如何保证数组数据左右分区
     * 选择最后一个值作为标准,其余为待排序去
     * 默认起始下标为大于last的 数据区
     * 从待排序区中选择小于last.与 大数据的初始下标swap。
     *
     * @param arr
     * @param first
     * @param last
     * @return
     */
    public static int partition(int[] arr, int first, int last) {
        int pivot = arr[last];
        int i = first;
        for (int j = first; j < last; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    int swap = arr[j];
                    arr[j] = arr[i];
                    arr[i] = swap;
                }
                i++;
            }
        }

        arr[last] = arr[i];
        arr[i] = pivot;
        return i;
    }

    public static int kthSmallest(int[] arr, int k) {
        if (arr.length < k) {
            return -1;
        }
        kthPartition(arr, k - 1, 0, arr.length - 1);
        return arr[k - 1];
    }


    public static void kthPartition(int[] arr, int k, int first, int last) {
        if (first == last) {
            return;
        }
        int pivot = arr[last];
        int i = first;
        for (int j = first; j < last; j++) {
            if (arr[j] < pivot) {
                if (i != j) {
                    int swap = arr[j];
                    arr[j] = arr[i];
                    arr[i] = swap;
                }
                i++;
            }
        }

        arr[last] = arr[i];
        arr[i] = pivot;
        if (k == i) {
            return;
        }

        if (k < i) {
            kthPartition(arr, k, first, i - 1);
        } else {
            kthPartition(arr, k, i + 1, last);
        }


    }


    /**
     * 桶排序
     * 时间复杂度度 O(n)
     * 空间复杂度O(n)
     * <p>
     * 在固定数据范围内，将要排序的数据范围分组。
     * 带排序数据安装分区一次放入，达到有序，最后进行整合
     * 非原地排序
     * <p>
     * 适合数据量远大于数据范围的场景。 外部排序
     * 适合数据量很大的外部排序
     * <p>
     * 对于桶的大小， 需要预留出来 余数部分
     *
     * @param arr
     */
    public static void bucketSort(int[] arr, int bucketSize) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        // +1 保证整除后 多余分区
        int bucketCount = (max - min) / bucketSize + 1;

        int[][] buckets = new int[bucketCount][bucketSize];
        int[] indexArr = new int[bucketCount];
        for (int i = 0; i < arr.length; i++) {
            int bucketIndex = (arr[i] - min) / bucketSize;

            if (indexArr[bucketIndex] == buckets[bucketIndex].length) {
                ensureCapacity(buckets, bucketIndex);
            }

            buckets[bucketIndex][indexArr[bucketIndex]++] = arr[i];
        }
        int arrIndex = 0;
        //分组快排 原地排序
        for (int i = 0; i < bucketCount; i++) {
            sefQuicklyC(buckets[i], 0, indexArr[i] - 1);
            for (int j = 0; j < indexArr[i]; j++) {
                arr[arrIndex++] = buckets[i][j];
            }
        }
    }

    /**
     * 计数排序
     * 思路: 在有限数据区域内，大数据量排序
     * 首先映射所有数据段结束位置，插入数据
     * 空间复杂度 O(n)
     * 时间复杂度 o(n)
     * <p>
     * 注意 设值的时，需要将数据从后往前便利。保证算法稳定
     *
     * @param arr
     */
    public static void countSort(int[] arr) {
        int min = arr[0];
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        if (max == min) {
            return;
        }

        int align = min;
        int[] countArr = new int[max - align + 1];

        for (int i = 0; i < arr.length; i++) {
            countArr[arr[i] - align]++;
        }

        //累加

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] = countArr[i] + countArr[i - 1];
        }

        int[] temp = new int[arr.length];

        //这里有问题：为了保证计数排序是有序的 需要将数组从后往前便利
//        for (int i = 0; i < arr.length; i++) {
//            temp[--countArr[arr[i] - align]] = arr[i];
//        }

        //从后往前便利，保证稳定
        for (int i = arr.length - 1; i >= 0; i--) {
            temp[--countArr[arr[i] - align]] = arr[i];
        }

        for (int i = 0; i < temp.length; i++) {
            arr[i] = temp[i];
        }
    }

    /**
     * 基数排序:
     * 使低位到高位数值依次相对有序，同事保证每次排序的稳定性。那么将所有排序结束后，整体则有序
     *
     * @param arr
     */
    public static void radixSort(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        int[] temp = new int[arr.length];

        for (int i = 1; max / i > 0; i = i * 10) {
            countSortC(arr, temp, i);
        }
    }

    /**
     * 假设我们现在需要对 D，a，F，B，c，A，z 这个字符串进行排序，
     * 要求将其中所有小写字母都排在大写字母的前面，但小写字母内部和大写字母内部不要求有序。
     * 比如经过排序之后为 a，c，z，D，F，B，A，这个如何来实现呢？
     *
     * @param arr
     */
    public static void partitionV(char[] arr) {
        int first = 0;
        int last = arr.length - 1;
        while (first < last) {
            while ((int) arr[first] >= 97) {
                first++;
            }
            while ((int) arr[last] <= 90) {
                last--;
            }
            if (first < last) {
                char swap = arr[first];
                arr[first] = arr[last];
                arr[last] = swap;
            }
        }
    }

    private static void countSortC(int[] arr, int[] tmp, int exp) {
        int[] countArr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i] / exp % 10;
            countArr[index]++;
        }

        for (int i = 1; i < countArr.length; i++) {
            countArr[i] += countArr[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            int index = arr[i] / exp % 10;
            tmp[--countArr[index]] = arr[i];
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = tmp[i];
        }
    }

    private static void sefQuicklyC(int[] arr, int first, int last) {
        if (first >= last) {
            return;
        }
        int partition = partitionC(arr, first, last);
        sefQuicklyC(arr, first, partition - 1);
        sefQuicklyC(arr, partition + 1, last);
    }

    public static int partitionC(int[] arr, int first, int last) {
        int pivot = arr[last];
        int gtIndex = first;
        for (int i = first; i < last; i++) {
            if (arr[i] < pivot) {
                if (gtIndex != i) {
                    swap(arr, gtIndex, i);
                }
                gtIndex++;
            }
        }
        swap(arr, last, gtIndex);
        return gtIndex;
    }

    public static void swap(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void ensureCapacity(int[][] buckets, int index) {
        int[] arr = buckets[index];
        int capacity = arr.length * 2;

        int[] newArr = new int[capacity];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        buckets[index] = newArr;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{6, 2, 3, 2, 1, 5, 4};
//        int[] arr = new int[]{6, 2, 1, 3, 4, 5};
        int[] arr = new int[]{1, -1, -2, -2, 3, 3, 4, 1, 4, 0, 0, 1};
//        bubbleSort(arr);
//        insertSort(arr);
//        selectionSort(arr);
//        mergeSort(arr);
//        quickSort(arr);
//        int i1 = kthSmallest(arr, 2);
//        System.out.println(i1);
//        bucketSort(arr, 2);
        countSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
        System.out.println();
        testRadix();
        System.out.println();
        testPartitionC();
    }

    public static void testRadix() {
        int[] arr = new int[]{123456, 654321, 123544, 123333, 124555, 654322, 1234333};
        radixSort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + ",");
        }
    }

    public static void testPartitionC() {
//        char[] arr = new char[]{'c','C','d','D','e','A','a','b'};
        char[] arr = new char[]{'c'};
        partitionV(arr);
        System.out.println(arr);
    }
}
