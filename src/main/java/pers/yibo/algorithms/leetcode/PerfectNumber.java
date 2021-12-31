package pers.yibo.algorithms.leetcode;

/**
 * 507. 完美数
 * <p>
 * https://leetcode-cn.com/problems/perfect-number
 * <p>
 * 对于一个正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * <p>
 * 给定一个整数n，如果是完美数，返回 true，否则返回 false
 *
 * @author yibo
 * @date 2021-12-31 09:23
 **/
public class PerfectNumber {
    public boolean checkPerfectNumber(int num) {

        if (num == 1) {
            return false;
        }

        int ans = 1;

        for (int i = 2; i < Math.sqrt(num); i++) {
            if (num % i == 0) {
                ans += num / i + i;
            }
        }

        return ans == num;
    }
}
