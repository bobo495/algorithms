package pers.yibo.algorithms.sort;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * 归并排序，自底向上
 * <p>
 * 递归实现的归并排序是算法设计中分治思想的典型应用。 我们将一个大问题分割成小问题分 别解决， 然后用所有小问题的答案来解决整个大问题。 尽管我们考虑的问题是归并两个大数组， 实际上我们归并的数组大多数都非常小。
 * <p>
 * 实现归并排序的另一种方法是先归并那些微型数组， 然后再成对归并得到的子数组，如此这般，直到我们将整个数组归并在一起。
 * <p>
 * 对于长度为 N 的任意数组，自底向上的归并排序需要 1/2 N lg N 至 N lg N 次比较，最多 访问数组 6 N lg N 次。
 *
 * @author yibo
 * @date 2021-11-10 15:11
 **/
public class MergeBottomUpSort extends AbstractMergeSort implements SortFunction {
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Comparable<? super T>> void sort(T[] a) {
        T[] extra = (T[]) Array.newInstance(Comparable.class, a.length);
        // 执行merge的子数组长度
        for (int step = 1; step < a.length; step += step) {
            for (int i = 0; i < a.length - step; i += step + step) {
                merge(a, extra, i, i + step - 1, Math.min(a.length - 1, i + step + step - 1));
            }
        }
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T> void sort(T[] a, Comparator<T> comparator) {
        T[] extra = (T[]) Array.newInstance(Comparable.class, a.length);
        // 执行merge的子数组长度
        for (int step = 1; step < a.length; step += step) {
            for (int i = 0; i < a.length - step; i += step + step) {
                merge(a, extra, comparator, i, i + step - 1, Math.min(a.length - 1, i + step + step - 1));
            }
        }
    }

    @Override
    public void sort(int[] a) {
        int[] extra = new int[a.length];
        // 执行merge的子数组长度
        for (int step = 1; step < a.length; step += step) {
            for (int i = 0; i < a.length - step; i += step + step) {
                merge(a, extra, i, i + step - 1, Math.min(a.length - 1, i + step + step - 1));
            }
        }
    }
}
