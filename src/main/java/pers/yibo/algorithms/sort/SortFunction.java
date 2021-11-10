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
     <T> void sort(T[] a, Comparator<T> comparator) ;

    /**
     * int类型数组比较
     *
     * @param a int数组
     */
     void sort(int[] a);

}
