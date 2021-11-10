package pers.yibo.algorithms.sort;


import java.util.Comparator;

/**
 * 选择排序
 *
 * @author yibo
 * @date 2021-11-09 17:43
 **/
public class Selection implements SortFunction {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i + 1; j < a.length; j++) {
                if (a[j].compareTo(a[min]) < 0) {
                    min = j;
                }
            }
            Utils.exchange(a, i, min);
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
            Utils.exchange(a, i, min);
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
            Utils.exchange(a, i, min);
        }
    }
}
