package pers.yibo.algorithms.sort;

import java.util.Comparator;
import java.util.Random;

/**
 * 快速排序，三向切分
 * <p>
 * Dijkstra 的解法如“三向切分的快速排序”中极为简洁的切分代码。
 * 它从左到右遍历数组一次，维护一个指针 lt 使得 a[lo..lt-1] 中的元素都小于 v ，
 * 一个指针 gt 使得 a[gt+1..hi] 中 的元素都大于 v ，
 * 一个指针 i 使得 a[lt..i-1] 中的元素都等于 v 。
 *
 * @author yibo
 * @date 2021-11-10 16:33
 **/
public class Quick3WaySort implements SortFunction {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    @Override
    public <T> void sort(T[] a, Comparator<T> comparator) {
        shuffle(a);
        sort(a, comparator, 0, a.length - 1);
    }

    @Override
    public void sort(int[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    /**
     * 三向切分方法
     * <p>
     * 对子数组 a[low,high] 和切分元素 splitElement
     * <p>
     * 使得：a[low,lt-1] < splitElement = a[lt,gt] < a[gt+1,high]
     *
     * @param a    待排序数组
     * @param low  低位下标
     * @param high 高位下标
     */
    public <T extends Comparable<? super T>> void sort(T[] a, int low, int high) {
        if (high <= low) {
            return;
        }
        // lt 低位指针，index 当前遍历指针, gt 高位指针
        int lt = low, index = low + 1, gt = high;
        // 分割元素
        T splitElement = a[low];
        while (index <= gt) {
            int compare = a[index].compareTo(splitElement);
            if (compare < 0) {
                // 低位元素移动至低位区
                exchange(a, lt++, index++);
            } else if (compare > 0) {
                // 高位元素移动至高位区，此时index不前进
                exchange(a, index, gt--);
            } else {
                index++;
            }
        }
        sort(a, low, lt - 1);
        sort(a, gt + 1, high);
    }

    /**
     * 三向切分方法
     * <p>
     * 对子数组 a[low,high] 和切分元素 splitElement
     * <p>
     * 使得：a[low,lt-1] < splitElement = a[lt,gt] < a[gt+1,high]
     *
     * @param a    待排序数组
     * @param low  低位下标
     * @param high 高位下标
     */
    public <T> void sort(T[] a, Comparator<T> comparator, int low, int high) {
        if (high <= low) {
            return;
        }
        // lt 低位指针，index 当前遍历指针, gt 高位指针
        int lt = low, index = low + 1, gt = high;
        // 分割元素
        T splitElement = a[low];
        while (index <= gt) {
            int compare = comparator.compare(a[index], splitElement);
            if (compare < 0) {
                // 低位元素移动至低位区
                exchange(a, lt++, index++);
            } else if (compare > 0) {
                // 高位元素移动至高位区，此时index不前进
                exchange(a, index, gt--);
            } else {
                index++;
            }
        }
        sort(a, comparator, low, lt - 1);
        sort(a, comparator, gt + 1, high);
    }


    /**
     * 三向切分方法
     * <p>
     * 对子数组 a[low,high] 和切分元素 splitElement
     * <p>
     * 使得：a[low,lt-1] < splitElement = a[lt,gt] < a[gt+1,high]
     *
     * @param a    待排序数组
     * @param low  低位下标
     * @param high 高位下标
     */
    public void sort(int[] a, int low, int high) {
        if (high <= low) {
            return;
        }
        // lt 低位指针，index 当前遍历指针, gt 高位指针
        int lt = low, index = low + 1, gt = high;
        // 分割元素
        int splitElement = a[low];
        while (index <= gt) {
            int compare = Integer.compare(a[index], splitElement);
            if (compare < 0) {
                // 低位元素移动至低位区
                exchange(a, lt++, index++);
            } else if (compare > 0) {
                // 高位元素移动至高位区，此时index不前进
                exchange(a, index, gt--);
            } else {
                index++;
            }
        }
        sort(a, low, lt - 1);
        sort(a, gt + 1, high);
    }


    /**
     * 随机打乱数组
     *
     * @param a 待排序数组
     */
    private void shuffle(int[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, random.nextInt(a.length - 1));
        }
    }

    /**
     * 随机打乱数组
     *
     * @param a 待排序数组
     */
    private <T> void shuffle(T[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, random.nextInt(a.length - 1));
        }
    }
}
