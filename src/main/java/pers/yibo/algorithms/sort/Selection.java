package pers.yibo.algorithms.sort;


/**
 * 选择排序
 *
 * @author yibo
 * @date 2021-11-09 17:43
 **/
public class Selection {
    public static <T extends Comparable<T>> void sort(T[] a) {
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
}
