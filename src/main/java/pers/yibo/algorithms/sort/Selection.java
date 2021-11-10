package pers.yibo.algorithms.sort;


import java.util.Comparator;

/**
 * 选择排序
 *
 * @author yibo
 * @date 2021-11-09 17:43
 **/
public class Selection {
    /**
     * 可比较类型排序实现
     *
     * @param a   数组
     * @param <T> 继承Comparable的类
     */
    public static <T extends Comparable<? super T>> void sort(T[] a) {
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

    /**
     * 自定义比较器
     *
     * @param a          数组
     * @param comparator 比较器
     * @param <T>        任意封装类
     */
    public static <T> void sort(T[] a, Comparator<T> comparator) {
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

    /**
     * int类型数组比较
     *
     * @param a int数组
     */
    public static void sort(int[] a) {
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
