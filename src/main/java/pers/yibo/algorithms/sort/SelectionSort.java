package pers.yibo.algorithms.sort;


import java.util.Comparator;

/**
 * 选择排序
 * <p>
 * 对于长度为 N 的数组，选择排序需要大约 N^2 /2 次比较和 N 次交换。
 * <p>
 * 1. 运行时间和输入无关。为了找出最小的元素而扫描一遍数组并不能为下一遍扫描提供什么信息。
 * 这种性质在某些情况下是缺点，因为使用选择排序的人可能会惊讶地发现，
 * 一个已经有序的数组或是主键全部相等的数组和一个元素随机排列的数组所用的排序时间竟然一样长！
 * <p>
 * 2. 数据移动是最少的。每次交换都会改变两个数组元素的值，因此选择排序用了 N 次交换——交换次数和数组的大小是线性关系。
 *
 * @author yibo
 * @date 2021-11-09 17:43
 **/
public class SelectionSort implements SortFunction {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    @Override
    public <T> void sort(T[] a, Comparator<T> comparator) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (comparator.compare(a[j], a[min]) < 0) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }

    @Override
    public void sort(int[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j] < a[min]) {
                    min = j;
                }
            }
            exchange(a, i, min);
        }
    }
}
