package pers.yibo.algorithms.leetcode;

/**
 * 400. 第 N 位数字
 * <p>
 * 给你一个整数 n ，请你在无限的整数序列 [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...] 中找出并返回第 n 位数字。
 * <p>
 * 9 - 99 - 999 - 9999 - 99999
 *
 * @author yibo
 * @date 2021-11-30 09:16
 **/
public class NthDigit {
    public int findNthDigit(int n) {
        // 当前位数，数字位数
        int t = 0;
        // 当前位数的最大数字
        int max = 1;
        // 当前位数的最大数字个数
        long count = 0;
        while (count < n) {
            t++;
            max *= 10;
            count += (long) (max - max / 10) * t;
        }
        // 当前所在位数距离max差距
        int dist = (int) ((count - n) / t);
        // 在第几位
        int m = (int) ((count - n) % t);
        // 目标数字
        int number = max - dist - 1;

        while (m > 0) {
            number /= 10;
            m--;
        }

        return number % 10;
    }

    public static void main(String[] args) {
        NthDigit n = new NthDigit();
        System.out.println(n.findNthDigit(15));
    }
}
