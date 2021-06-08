package com.grubby.klc.sort;

/**
 * 有序数组中
 * 折半思路找到那个值
 * <p>
 * 1.查找第一个值等于给定值的元素
 * 2.查找最后一个值等于给定值的元素
 * 3.查找第一个大于等于给定值的元素
 * 4.查找最后一个小于等于给定值的元素
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

    /**
     * 左右逼近原则
     *
     * @param arr
     * @param key
     * @return
     */
    public static int findFirstEqKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                if (mid != 0 && arr[mid - 1] == key) {
                    high = mid - 1;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于的key
     *
     * @param arr
     * @param key
     * @return
     */
    public static int findLastLteKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low >> 1);
            if (arr[mid] > key) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] > key) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    public static int findLastEqKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low >> 1);
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                if (mid == arr.length - 1 || arr[mid + 1] != key) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            }
        }
        return -1;
    }

    /**
     * @param arr
     * @param key
     * @return
     */
    public static int findFirstGteKey(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + (high - low >> 1);

            if (key > arr[mid]) {
                low = mid + 1;
                // key <= arr[mid];
            } else {
                if (mid == 0 || arr[mid - 1] < key) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 查找有序循环列表中的key位置
     *
     * @param arr
     * @param key
     * @return
     */
    public static int findCircle(int[] arr, int key) {
        // find pivot
        int partition = partition(arr);

        int left = findKey(arr, key, 0, partition - 1);

        if (left > -1) {
            return left;
        }

        int right = findKey(arr, key, partition, arr.length - 1);

        if (right > -1) {
            return right;
        }

        return -1;
    }

    public static int findKey(int[] arr, int key, int first, int last) {
        while (first <= last) {
            int mid = first + (last - first >> 1);
            if (key > arr[mid]) {
                first = mid + 1;
            } else if (key < arr[mid]) {
                last = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int partition(int[] arr) {
        if (arr.length == 0) {
            return -1;
        }

        int low = 1;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low >> 1);
            if (arr[mid] >= arr[0]) {
                low = mid + 1;
            } else {
                if (mid == 1 || arr[mid - 1] >= arr[0]) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        int[] arr = new int[]{0};
//        int search = search(arr, 1);
//        System.out.println(search);
//        int[] arr = new int[]{1, 2, 3, 3, 3, 4, 5, 6};
////        int lastEqKey = findLastLteKey(arr, 3);

        int[] arr = new int[]{4,5,6,1,2,3};
        int circle = findCircle(arr, 2);
        System.out.println(circle);
//        testFirst();
    }

    public static void testFirst() {
        int[] arr = new int[]{1, 2, 3, 3, 3, 4, 5, 6};
        int firstEqKey = findFirstEqKey(arr, 3);
        System.out.println(firstEqKey);
    }


}
