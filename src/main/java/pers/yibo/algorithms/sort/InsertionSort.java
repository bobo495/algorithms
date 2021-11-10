package pers.yibo.algorithms.sort;

import java.util.Comparator;

/**
 * 插入排序
 * <p>
 * 对于随机排列的长度为 N 且主键不重复的数组，平均情况下插入排序需要～ N^2 /4 次比较以及～ N^2 /4 次交换。
 * 最坏情况下需要～ N^2 /2 次比较和～ N^2 /2 次交换，最好情况下需要 N-1 次比较和 0 次交换。
 *
 * @author yibo
 * @date 2021-11-10 10:22
 **/
public class InsertionSort implements SortFunction {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            // 将第i个元素插入到0-i中的正确位置
            for (int j = i; j > 0 && a[j].compareTo(a[j - 1]) < 0; j--) {
                exchange(a, j - 1, j);
            }
        }
    }

    @Override
    public <T> void sort(T[] a, Comparator<T> comparator) {
        for (int i = 0; i < a.length; i++) {
            // 将第i个元素插入到0-i中的正确位置
            for (int j = i; j > 0 && comparator.compare(a[j], a[j - 1]) < 0; j--) {
                exchange(a, j - 1, j);
            }
        }
    }

    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            // 将第i个元素插入到0-i中的正确位置
            for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
                exchange(a, j - 1, j);
            }
        }
    }
}
