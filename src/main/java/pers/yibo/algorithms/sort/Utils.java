package pers.yibo.algorithms.sort;

/**
 * 元素交换/排序验证
 *
 * @author yibo
 * @date 2021-11-09 17:44
 **/
public class Utils {
    /**
     * 交换数组a中a[i]和a[j]
     *
     * @param a   数组
     * @param i   数组下标
     * @param j   数组下标
     * @param <T> 可排序类型
     */
    public static <T extends Comparable<T>> void exchange(T[] a, int i, int j) {
        T tmp = a[i];
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
    public static <T extends Comparable<T>> boolean isSorted(T[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
}
