package pers.yibo.algorithms.sort;

import java.util.Comparator;

/**
 * 归并排序的合并方法
 *
 * @author yibo
 * @date 2021-11-10 15:29
 **/
abstract class AbstractMergeSort {

    /**
     * 合并low - mid和mid+1 - high（两段子数组分别有序）
     *
     * @param a     待排序数组
     * @param extra 已分配额外空间
     * @param low   低位下标
     * @param mid   中间下标
     * @param high  高位下标
     */
     <T extends Comparable<? super T>> void merge(T[] a, T[] extra, int low, int mid, int high) {
        // 将low-high区间数组a copy 到数组 extra
        System.arraycopy(a, low, extra, low, high + 1 - low);

        // 低位区子数组下标
        int lowIndex = low;
        // 高位区子数组下标
        int highIndex = mid + 1;
        // 合并两端子数组回a
        for (int i = low; i <= high; i++) {
            if (lowIndex > mid) {
                // 低位区已添加完毕
                a[i] = extra[highIndex++];
            } else if (highIndex > high) {
                // 高位区已添加完毕
                a[i] = extra[lowIndex++];
            } else if (extra[lowIndex].compareTo(extra[highIndex]) < 0) {
                // 低位区最小元素小于高位区最小元素，则添加低位区元素
                a[i] = extra[lowIndex++];
            } else {
                // 添加高位区元素
                a[i] = extra[highIndex++];
            }
        }
    }

    /**
     * 合并low - mid和mid+1 - high（两段子数组分别有序）
     *
     * @param a     待排序数组
     * @param extra 已分配额外空间
     * @param low   低位下标
     * @param mid   中间下标
     * @param high  高位下标
     */
    <T> void merge(T[] a, T[] extra, Comparator<T> comparator, int low, int mid, int high) {
        // 将low-high区间数组a copy 到数组 extra
        System.arraycopy(a, low, extra, low, high + 1 - low);

        // 低位区子数组下标
        int lowIndex = low;
        // 高位区子数组下标
        int highIndex = mid + 1;
        // 合并两端子数组回a
        for (int i = low; i <= high; i++) {
            if (lowIndex > mid) {
                // 低位区已添加完毕
                a[i] = extra[highIndex++];
            } else if (highIndex > high) {
                // 高位区已添加完毕
                a[i] = extra[lowIndex++];
            } else if (comparator.compare(extra[lowIndex], extra[highIndex]) < 0) {
                // 低位区最小元素小于高位区最小元素，则添加低位区元素
                a[i] = extra[lowIndex++];
            } else {
                // 添加高位区元素
                a[i] = extra[highIndex++];
            }
        }
    }

    /**
     * 合并low - mid和mid+1 - high（两段子数组分别有序）
     *
     * @param a     待排序数组
     * @param extra 已分配额外空间
     * @param low   低位下标
     * @param mid   中间下标
     * @param high  高位下标
     */
     void merge(int[] a, int[] extra, int low, int mid, int high) {
        // 将low-high区间数组a copy 到数组 extra
        System.arraycopy(a, low, extra, low, high + 1 - low);

        // 低位区子数组下标
        int lowIndex = low;
        // 高位区子数组下标
        int highIndex = mid + 1;
        // 合并两端子数组回a
        for (int i = low; i <= high; i++) {
            if (lowIndex > mid) {
                // 低位区已添加完毕
                a[i] = extra[highIndex++];
            } else if (highIndex > high) {
                // 高位区已添加完毕
                a[i] = extra[lowIndex++];
            } else if (extra[lowIndex] < extra[highIndex]) {
                // 低位区最小元素小于高位区最小元素，则添加低位区元素
                a[i] = extra[lowIndex++];
            } else {
                // 添加高位区元素
                a[i] = extra[highIndex++];
            }
        }
    }
}
