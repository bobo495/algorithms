package pers.yibo.algorithms.sort;

import java.util.Comparator;
import java.util.Random;

/**
 * 快速排序
 * <p>
 * 快速排序是一种分治的排序算法。它将一个数组分成两个子数组，将两部分独立地排序。
 * 快速排序和归并排序是互补的：归并排序将数组分成两个子数组分别排序，并将有序的子数组归并以将整个数组排序；
 * 而快速排序将数组排序的方式则是当两个子数组都有序时整个数组也就自然有序了。在第一种情况中，递归调用发生在处理整个数组之前；
 * 在第二种情况中，递归调用发生在处理整个数组之后。在归并排序中，一个数组被等分为两半；在快速排序中，切分（partition）的位置取决于数组的内容。
 * <p>
 * 将长度为 N 的无重复数组排序，快速排序平均需要 ~2 N ln N 次比较（以及 1/6 的交换）。
 * <p>
 * 快速排序最多需要约 N^2 /2 次比较，但随机打乱数组能够预防这种情况。
 * <p>
 * 尽管快速排序有很多优点，它的基本实现仍有一个潜在的缺点：在切分不平衡时这个程序可能会极为低效。
 * 例如，如果第一次从最小的元素切分，第二次从第二小的元素切分，如此这般，每次调用只会移除一个元素。这会导致一个大子数组需要切分很多次。我们要在快速排序前将数组随机排序的 主要原因就是要避免这种情况。
 *
 * @author yibo
 * @date 2021-11-10 15:41
 **/
public class QuickSort implements SortFunction {

    @Override
    public <T extends Comparable<? super T>> void sort(T[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    @Override
    public <T> void sort(T[] a, Comparator<T> comparator) {
        shuffle(a);
        sort(a, comparator, 0, a.length - 1);
    }

    @Override
    public void sort(int[] a) {
        shuffle(a);
        sort(a, 0, a.length - 1);
    }

    private <T extends Comparable<? super T>> void sort(T[] a, int low, int high) {
        if (high <= low) {
            // 子数组元素数量 <= 1
            return;
        }
        int splitIndex = partition(a, low, high);
        sort(a, low, splitIndex - 1);
        sort(a, splitIndex + 1, high);
    }

    private <T> void sort(T[] a, Comparator<T> comparator, int low, int high) {
        if (high <= low) {
            // 子数组元素数量 <= 1
            return;
        }
        int splitIndex = partition(a, comparator, low, high);
        sort(a, comparator, low, splitIndex - 1);
        sort(a, comparator, splitIndex + 1, high);
    }

    private void sort(int[] a, int low, int high) {
        if (high <= low) {
            // 子数组元素数量 <= 1
            return;
        }
        int splitIndex = partition(a, low, high);
        sort(a, low, splitIndex - 1);
        sort(a, splitIndex + 1, high);
    }

    /**
     * 对子数组进行切分，切分标准为子数组第一个元素
     *
     * @param a    待排序数组
     * @param low  子数组开始下标
     * @param high 子数组结束下标
     * @return 低位区和高位区的分界线
     */
    private <T extends Comparable<? super T>> int partition(T[] a, int low, int high) {
        // 低位指针和高位指针
        int lowIndex = low, highIndex = high + 1;
        // 记录切分元素的值
        T splitElement = a[low];
        // 将子数组分为低位区和高位区：低位区均小于splitElement，高位区均大于splitElement
        while (true) {
            // 找到下一个应该在高位区的元素
            while (a[++lowIndex].compareTo(splitElement) < 0) {
                if (lowIndex == high) {
                    break;
                }
            }
            // 找到下一个应该在低位区的元素
            while (splitElement.compareTo(a[--highIndex]) < 0) {
                if (highIndex == low) {
                    break;
                }
            }
            // 当低位区指针超过高位区指针时说明搜索结束
            if (lowIndex >= highIndex) {
                break;
            }
            // 高低位区值交换
            exchange(a, lowIndex, highIndex);
        }
        // highIndex为低位区和高位区分界线，因此将其于splitElement下标交换
        exchange(a, low, highIndex);
        return highIndex;
    }

    /**
     * 对子数组进行切分，切分标准为子数组第一个元素
     *
     * @param a    待排序数组
     * @param low  子数组开始下标
     * @param high 子数组结束下标
     * @return 低位区和高位区的分界线
     */
    private <T> int partition(T[] a, Comparator<T> comparator, int low, int high) {
        // 低位指针和高位指针
        int lowIndex = low, highIndex = high + 1;
        // 记录切分元素的值
        T splitElement = a[low];
        // 将子数组分为低位区和高位区：低位区均小于splitElement，高位区均大于splitElement
        while (true) {
            // 找到下一个应该在高位区的元素
            while (comparator.compare(a[++lowIndex], splitElement) < 0) {
                if (lowIndex == high) {
                    break;
                }
            }
            // 找到下一个应该在低位区的元素
            while (comparator.compare(splitElement, a[--highIndex]) < 0) {
                if (highIndex == low) {
                    break;
                }
            }
            // 当低位区指针超过高位区指针时说明搜索结束
            if (lowIndex >= highIndex) {
                break;
            }
            // 高低位区值交换
            exchange(a, lowIndex, highIndex);
        }
        // highIndex为低位区和高位区分界线，因此将其于splitElement下标交换
        exchange(a, low, highIndex);
        return highIndex;
    }

    /**
     * 对子数组进行切分，切分标准为子数组第一个元素
     *
     * @param a    待排序数组
     * @param low  子数组开始下标
     * @param high 子数组结束下标
     * @return 低位区和高位区的分界线
     */
    private int partition(int[] a, int low, int high) {
        // 低位指针和高位指针
        int lowIndex = low, highIndex = high + 1;
        // 记录切分元素的值
        int splitElement = a[low];
        // 将子数组分为低位区和高位区：低位区均小于splitElement，高位区均大于splitElement
        while (true) {
            // 找到下一个应该在高位区的元素
            while (a[++lowIndex] < splitElement) {
                if (lowIndex == high) {
                    break;
                }
            }
            // 找到下一个应该在低位区的元素
            while (splitElement < a[--highIndex]) {
                if (highIndex == low) {
                    break;
                }
            }
            // 当低位区指针超过高位区指针时说明搜索结束
            if (lowIndex >= highIndex) {
                break;
            }
            // 高低位区值交换
            exchange(a, lowIndex, highIndex);
        }
        // highIndex为低位区和高位区分界线，因此将其于splitElement下标交换
        exchange(a, low, highIndex);
        return highIndex;
    }

    /**
     * 随机打乱数组
     *
     * @param a 待排序数组
     */
    private void shuffle(int[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, random.nextInt(a.length - 1));
        }
    }

    /**
     * 随机打乱数组
     *
     * @param a 待排序数组
     */
    private <T> void shuffle(T[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, random.nextInt(a.length - 1));
        }
    }
}
