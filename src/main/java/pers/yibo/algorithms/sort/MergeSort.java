package pers.yibo.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * 归并排序，自顶向下方式
 * <p>
 * 要将一个数组 排序，可以先（递归地）将它分成两半分别排序，然后将结果归并起来。你将会看到，归并排序最 吸引人的性质是它能够保证将任意长度为 N 的数组排序所需时间和 N log N 成正比；它的主要缺点 则是它所需的额外空间和 N 成正比。
 * <p>
 * 实现归并的一种直截了当的办法是将两个不同的有序数组归并到第三个数组中，两个数组中的 元素应该都实现了 Comparable 接口。实现的方法很简单，创建一个适当大小的数组然后将两个输 入数组中的元素一个个从小到大放入这个数组中。
 * <p>
 * 对于长度为 N 的任意数组，自顶向下的归并排序需要 ½NlgN 至 NlgN 次比较。
 *
 * @author yibo
 * @date 2021-11-10 11:39
 **/
public class MergeSort implements SortFunction {

    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> void sort(T[] a) {
        T[] extra = (T[]) Array.newInstance(Comparable.class, a.length);
        sort(a, extra, 0, a.length - 1);
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] a, Comparator<T> comparator) {
        T[] extra = (T[]) Array.newInstance(Comparable.class, a.length);
        sort(a, extra, comparator, 0, a.length - 1);
    }

    @Override
    public void sort(int[] a) {
        int[] extra = new int[a.length];
        sort(a, extra, 0, a.length - 1);
    }

    /**
     * 递归二分排序
     *
     * @param a     待排序数组
     * @param extra 已分配额外空间
     * @param low   低位下标
     * @param high  高位下标
     */
    private <T extends Comparable<? super T>> void sort(T[] a, T[] extra, int low, int high) {
        if (high <= low) {
            // 仅有一个元素则为有序
            return;
        }
        int mid = (low + high) / 2;
        // 低位区排序
        sort(a, extra, low, mid);
        // 高位区排序
        sort(a, extra, mid + 1, high);
        // 合并low - high 子序列
        merge(a, extra, low, mid, high);
    }

    /**
     * 递归二分排序
     *
     * @param a     待排序数组
     * @param extra 已分配额外空间
     * @param low   低位下标
     * @param high  高位下标
     */
    private <T> void sort(T[] a, T[] extra, Comparator<T> comparator, int low, int high) {
        if (high <= low) {
            // 仅有一个元素则为有序
            return;
        }
        int mid = (low + high) / 2;
        // 低位区排序
        sort(a, extra, comparator, low, mid);
        // 高位区排序
        sort(a, extra, comparator, mid + 1, high);
        // 合并low - high 子序列
        merge(a, extra, comparator, low, mid, high);
    }

    /**
     * 递归二分排序
     *
     * @param a     待排序数组
     * @param extra 已分配额外空间
     * @param low   低位下标
     * @param high  高位下标
     */
    private void sort(int[] a, int[] extra, int low, int high) {
        if (high <= low) {
            // 仅有一个元素则为有序
            return;
        }
        int mid = (low + high) / 2;
        // 低位区排序
        sort(a, extra, low, mid);
        // 高位区排序
        sort(a, extra, mid + 1, high);
        // 合并low - high 子序列
        merge(a, extra, low, mid, high);
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
    private <T extends Comparable<? super T>> void merge(T[] a, T[] extra, int low, int mid, int high) {
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
    private <T> void merge(T[] a, T[] extra, Comparator<T> comparator, int low, int mid, int high) {
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
    private void merge(int[] a, int[] extra, int low, int mid, int high) {
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
