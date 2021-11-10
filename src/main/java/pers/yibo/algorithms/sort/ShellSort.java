package pers.yibo.algorithms.sort;

import java.util.Comparator;

/**
 * 希尔排序
 * <p>
 * 希尔排序的思想是使数组中任意间隔为 h 的元素都是有序的。这样的数组被称为 h 有序数组。换 句话说，一个 h 有序数组就是 h 个互相独立的有序数组编织在一起组成的一个数组。
 * <p>
 * 希尔排序更高效的原因是它权衡了子数组的规模和有序性。排序之初，各个子数组都很短，排序之后子数组都是部分有序的，这两种情况都很适合插入排序。子数组部分有序的程度取决于递增 序列的选择。
 * <p>
 * 透彻理解希尔排序的性能至今仍然是一项挑战。实际上，希尔排序是我们唯一无法准确描述其对于乱序的数组的性能特征的排序方法。
 * <p>
 * 使用递增序列 1, 4, 13, 40, 121, 364…的希尔排序所需的比较次数不会超出 N 的若干倍乘以递增序列的长度。
 * <p>
 * https://zh.wikipedia.org/wiki/希尔排序
 *
 * @author yibo
 * @date 2021-11-10 10:56
 **/
public class ShellSort implements SortFunction {
    /**
     * 步长，默认步长为4（参考wiki，最好步长间隔为4）
     * <p>
     * 递增序列每次增加的步长
     */
    int step = 3;

    public ShellSort() {
    }

    public ShellSort(int step) {
        this.step = step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        int h = 1;
        // 构造递增序列 step * h + 1
        while (h < a.length / step) {
            h = step * h + 1;
        }
        /*
         h=1时，为选择排序。间隔h排序的目的是让数组先局部有序，从而减少选择排序执行交换的次数，降低选择排序的最坏情况
         */
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                // 间隔h的插入排序
                for (int j = i; j >= h && a[j].compareTo(a[j - h]) < 0; j -= h) {
                    exchange(a, j - h, j);
                }
            }
            h = h / step;
        }
    }

    @Override
    public <T> void sort(T[] a, Comparator<T> comparator) {
        int h = 1;
        // 构造递增序列 step * h + 1
        while (h < a.length / step) {
            h = step * h + 1;
        }
        /*
         h=1时，为选择排序。间隔h排序的目的是让数组先局部有序，从而减少选择排序执行交换的次数，降低选择排序的最坏情况
         */
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                // 间隔h的插入排序
                for (int j = i; j >= h && comparator.compare(a[j], a[j - h]) < 0; j -= h) {
                    exchange(a, j - h, j);
                }
            }
            h = h / step;
        }
    }

    @Override
    public void sort(int[] a) {
        int h = 1;
        // 构造递增序列 step * h + 1
        while (h < a.length / step) {
            h = step * h + 1;
        }
        /*
         h=1时，为选择排序。间隔h排序的目的是让数组先局部有序，从而减少选择排序执行交换的次数，降低选择排序的最坏情况
         */
        while (h >= 1) {
            for (int i = h; i < a.length; i++) {
                // 间隔h的插入排序
                for (int j = i; j >= h && a[j] < a[j - h]; j -= h) {
                    exchange(a, j - h, j);
                }
            }
            h = h / step;
        }
    }
}
