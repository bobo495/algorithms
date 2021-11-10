package pers.yibo.algorithms.sort;

import java.util.Arrays;

/**
 * @author yibo
 * @date 2021-11-09 17:59
 **/
public class Test {
    public static void main(String[] args) {
        // 选择排序
        int[] a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction selection = new SelectionSort();
        selection.sort(a);
        System.out.println(selection.isSorted(a));
        System.out.println(Arrays.toString(a));

        // 插入排序
        a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction insertion = new InsertionSort();
        insertion.sort(a);
        System.out.println(insertion.isSorted(a));
        System.out.println(Arrays.toString(a));

        // 希尔排序
        a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction shellSort = new ShellSort();
        shellSort.sort(a);
        System.out.println(shellSort.isSorted(a));
        System.out.println(Arrays.toString(a));

        // 归并排序，自顶向下
        a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction mergeSort = new MergeSort();
        mergeSort.sort(a);
        System.out.println(mergeSort.isSorted(a));
        System.out.println(Arrays.toString(a));
        Integer[] b = new Integer[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        mergeSort.sort(b);
        System.out.println(mergeSort.isSorted(b));
        System.out.println(Arrays.toString(b));

        // 归并排序，自底向上
        a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction mergeBottomUpSort = new MergeBottomUpSort();
        mergeBottomUpSort.sort(a);
        System.out.println(mergeBottomUpSort.isSorted(a));
        System.out.println(Arrays.toString(a));
        b = new Integer[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        mergeBottomUpSort.sort(b);
        System.out.println(mergeBottomUpSort.isSorted(b));
        System.out.println(Arrays.toString(b));

        // 快排
        a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction quickSort = new QuickSort();
        quickSort.sort(a);
        System.out.println(quickSort.isSorted(a));
        System.out.println(Arrays.toString(a));

        // 快排，三向切分
        a = new int[]{1, 5, 7, 3, 9, 4, 8, 5, 6, 2};
        SortFunction quick3WaySort = new Quick3WaySort();
        quick3WaySort.sort(a);
        System.out.println(quick3WaySort.isSorted(a));
        System.out.println(Arrays.toString(a));
    }
}
