package pers.yibo.algorithms.sort;

import java.util.Comparator;

/**
 * 排序功能需要实现的方法
 *
 * @author aoyb
 * 2021/11/10 10:22 上午
 */
public interface SortFunction {
    /**
     * 可比较类型排序实现
     *
     * @param a   数组
     * @param <T> 继承Comparable的类
     */
    <T extends Comparable<? super T>> void sort(T[] a);

    /**
     * 自定义比较器
     *
     * @param a          数组
     * @param comparator 比较器
     * @param <T>        任意封装类
     */
    <T> void sort(T[] a, Comparator<T> comparator);

    /**
     * int类型数组比较
     *
     * @param a int数组
     */
    void sort(int[] a);

    /**
     * 交换数组a中a[i]和a[j]
     *
     * @param a   数组
     * @param i   数组下标
     * @param j   数组下标
     * @param <T> 可排序类型
     */
    default <T> void exchange(T[] a, int i, int j) {
        T tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    /**
     * 交换int数组a中a[i]和a[j]
     *
     * @param a int数组
     * @param i 数组下标
     * @param j 数组下标
     */
    default void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    /**
     * 校验数组是否已排序
     *
     * @param a   数组
     * @param <T> 可排序类型
     * @return true-已排序，false-未排序
     */
    default <T extends Comparable<T>> boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验int数组是否已排序
     *
     * @param a int数组
     * @return true-已排序，false-未排序
     */
    default boolean isSorted(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
    }
}
