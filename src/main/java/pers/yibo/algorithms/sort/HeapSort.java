package pers.yibo.algorithms.sort;

import java.util.Comparator;

/**
 * 堆排序，参考{@link MaxPriorityQueue}原理实现，每次从队列中取出最大元素。
 * <p>
 * 由于仅用于排序，因此此处不需要考虑上浮操作，仅需要sink
 * <p>
 * 为避免构建额外空间，因此仅通过数组内交换实现
 * <p>
 * 将 N 个元素排序，堆排序只需少于（2 N lg N +2 N ）次比较（以及一半次数的交换）。
 *
 * @author yibo
 * @date 2021-11-11 14:59
 **/
public class HeapSort implements SortFunction {
    /**
     * 子节点数量
     */
    private final static int CHILD_SIZE = 2;

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        // 有序堆的最大索引下标 +1 (第0个元素无法下沉)
        int size = a.length;
        // 由于每次下沉index都会与2*index进行比较，因此仅需下沉一半的元素即可实现堆有序
        for (int i = size / CHILD_SIZE; i >= 0; i--) {
            sink(a, i + 1, size);
        }
        while (size > 1) {
            // 有序堆的最大元素替换到堆尾
            exchange(a, 0, --size);
            // 首位元素下沉
            sink(a, 1, size);
        }
    }

    @Override
    public <T> void sort(T[] a, Comparator<T> comparator) {
        // 有序堆的最大索引下标 +1 (第0个元素无法下沉)
        int size = a.length;
        // 由于每次下沉index都会与2*index进行比较，因此仅需下沉一半的元素即可实现堆有序
        for (int i = size / CHILD_SIZE; i >= 0; i--) {
            sink(a, comparator, i + 1, size);
        }
        while (size > 1) {
            // 有序堆的最大元素替换到堆尾
            exchange(a, 0, --size);
            // 首位元素下沉
            sink(a, comparator, 1, size);
        }
    }

    @Override
    public void sort(int[] a) {
        // 有序堆的最大索引下标 +1 (第0个元素无法下沉)
        int size = a.length;
        // 由于每次下沉index都会与2*index进行比较，因此仅需下沉一半的元素即可实现堆有序
        for (int i = size / CHILD_SIZE; i >= 0; i--) {
            sink(a, i + 1, size);
        }
        while (size > 1) {
            // 有序堆的最大元素替换到堆尾
            exchange(a, 0, --size);
            // 首位元素下沉
            sink(a, 1, size);
        }
    }

    /**
     * 数组内的元素下沉
     *
     * @param a     待排序数组
     * @param index 待下沉元素下标 +1
     * @param size  有序堆最大索引下标 +1
     * @param <T>   数组元素类型，继承{@link Comparable}
     */
    private <T extends Comparable<? super T>> void sink(T[] a, int index, int size) {
        while (index * CHILD_SIZE < size) {
            int childIndex = index * CHILD_SIZE;
            if (childIndex < size && a[childIndex - 1].compareTo(a[childIndex]) < 0) {
                childIndex++;
            }
            if (a[childIndex - 1].compareTo(a[index - 1]) < 0) {
                break;
            }
            exchange(a, index - 1, childIndex - 1);
            index = childIndex;
        }
    }

    /**
     * 数组内的元素下沉
     *
     * @param a          待排序数组
     * @param index      待下沉元素下标 +1
     * @param size       有序堆最大索引下标 +1
     * @param <T>        数组元素类型，继承{@link Comparable}
     * @param comparator 自定义比较器
     */
    private <T> void sink(T[] a, Comparator<T> comparator, int index, int size) {
        while (index * CHILD_SIZE < size) {
            int childIndex = index * CHILD_SIZE;
            if (childIndex < size && comparator.compare(a[childIndex - 1], a[childIndex]) < 0) {
                childIndex++;
            }
            if (comparator.compare(a[childIndex - 1], a[index - 1]) < 0) {
                break;
            }
            exchange(a, index - 1, childIndex - 1);
            index = childIndex;
        }
    }

    /**
     * 数组内的元素下沉
     *
     * @param a     待排序数组
     * @param index 待下沉元素下标 +1
     * @param size  有序堆最大索引下标 +1
     */
    private void sink(int[] a, int index, int size) {
        while (index * CHILD_SIZE < size) {
            int childIndex = index * CHILD_SIZE;
            if (childIndex < size && a[childIndex - 1] < a[childIndex]) {
                childIndex++;
            }
            if (a[childIndex - 1] < a[index - 1]) {
                break;
            }
            exchange(a, index - 1, childIndex - 1);
            index = childIndex;
        }
    }
}
