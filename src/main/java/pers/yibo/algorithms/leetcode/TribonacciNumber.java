package pers.yibo.algorithms.leetcode;

/**
 * 1137. 第 N 个泰波那契数
 *
 * @author yibo
 * @date 2021-11-16 10:35
 **/
public class TribonacciNumber {
    public int tribonacci(int n) {
        if (n == 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int[] res = new int[n + 1];
        res[1] = 1;
        res[2] = 1;
        for (int i = 3; i < n + 1; i++) {
            res[i] = res[i - 1] + res[i - 2] + res[i - 3];
        }
        return res[n];
    }
}
