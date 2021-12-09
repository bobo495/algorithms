package pers.yibo.algorithms.string;

import java.util.Arrays;

/**
 * LSD基数排序 - 低位优先的字符串排序
 * <p>
 * 低位优先的字符串排序算法能够稳定地将定长字符串排序。
 * <p>
 * 原理：从右到左，以此使用键索引计数法对每个字符进行分类排序
 * <p>
 * 键索引计数法排序 N 个键为 0 到 R-1 之间的整数的元素需要访问数组 11N + 4R+1 次。
 * <p>
 * 低位优先的字符串排序算法能够稳定地将定长字符串排序。
 * <p>
 * 对于基于 R 个字符的字母表的 N 个以长为 W 的字符串 为键的元素，低位优先的字符串排序需要访问 ~7WN + 3WR 次数组，使 用的额外空间与 N + R 成正比。
 *
 * @author yibo
 * @date 2021-12-09 10:59
 **/
public class LeastSignificantDigitRadixSort {
    /**
     * 每个BYTE对应8个BIT
     */
    private static final int BITS_PER_BYTE = 8;

    /**
     * 对字符串所有字符进行排序，要求所有字符串长度相同
     *
     * @param arr 待排序字符串数组
     */
    public static void sort(String[] arr) {
        if (arr.length == 0) {
            return;
        }
        sort(arr, arr[0].length());
    }

    /**
     * 对字符串前w个字符进行排序（要求所有字符串长度大于等于w）
     *
     * @param arr 待排序字符串数组
     * @param w   指定前w个字符进行排序
     */
    public static void sort(String[] arr, int w) {
        int arrLength = arr.length;
        // ascii 基数
        int radix = 256;
        // 临时字符串数组
        String[] aux = new String[arrLength];

        // 从字符串下标的w-1开始排序
        for (int d = w - 1; d >= 0; d--) {
            // 最多可以分radix个组，因此初始化count为radix+1
            int[] count = new int[radix + 1];

            // 计算每组有多少个元素
            for (String s : arr) {
                count[s.charAt(d) + 1]++;
            }

            // 计算每组开始的下标
            for (int i = 0; i < radix; i++) {
                count[i + 1] += count[i];
            }

            // 将字符串放到临时字符串数组aux中
            for (String s : arr) {
                // 找到s所在分组，获取分组的当前下标。将s放到aux对应位置，然后将该分组的下标+1
                aux[count[s.charAt(d)]++] = s;
            }

            // 临时分组的数据拷贝回去
            System.arraycopy(aux, 0, arr, 0, arrLength);
        }
    }

    /**
     * 对32位整数的降序排序，速度相比Arrays.sort()快约2-3倍。
     *
     * @param arr 待排序数组
     */
    public static void sort(int[] arr) {
        // int型有4bytes，32bits
        final int bits = 32;
        // 每byte的基数为0-255
        final int radix = 1 << BITS_PER_BYTE;
        // mask 0xFF
        final int mask = radix - 1;
        // 每个数字均有4 bytes
        final int w = bits / BITS_PER_BYTE;

        int arrLength = arr.length;
        int[] aux = new int[arrLength];

        for (int d = 0; d < w; d++) {
            // 最多可以分radix个组，因此初始化count为radix+1
            int[] count = new int[radix + 1];

            // 计算每组有多少元素
            for (int j : arr) {
                // 将j向右移动d个bytes，通过mask取当前最右侧的1个bytes
                int c = j >> BITS_PER_BYTE * d & mask;
                count[c + 1]++;
            }

            // 计算每组元素对应的起始下标
            for (int i = 0; i < radix; i++) {
                count[i + 1] += count[i];
            }

            // 最左边，需要考虑负号，0x80-0xFF是在0x00-0x7F前面的
            if (d == w - 1) {
                // 负数数量，首位为1
                int shift1 = count[radix] - count[radix / 2];
                // 正数数量，首位为0
                int shift2 = count[radix / 2];
                // 正数索引向后挪{shift1}
                for (int r = 0; r < radix / 2; r++) {
                    count[r] += shift1;
                }
                // 负数索引向前挪{shift2}
                for (int r = radix / 2; r < radix; r++) {
                    count[r] -= shift2;
                }
            }

            // 将元素放到临时分组中
            for (int j : arr) {
                int c = j >> BITS_PER_BYTE * d & mask;
                aux[count[c]++] = j;
            }

            // 将数据copy回去
            System.arraycopy(aux, 0, arr, 0, arrLength);
        }
    }


    public static void main(String[] args) {
        String[] testStr = new String[]{"4PGC938", "2IYE230", "1ICK750", "4JZY524"};
        sort(testStr);
        System.out.println(Arrays.toString(testStr));

        int[] test = new int[]{1, 4, 5, 79, 6, 2, -1, -4, -7, -10, 1610612735, -1610612735};
        sort(test);
        System.out.println(Arrays.toString(test));
    }
}
