package pers.yibo.algorithms.leetcode;

/**
 * 367. 有效的完全平方数
 * <p>
 * https://leetcode-cn.com/problems/valid-perfect-square
 * <p>
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * <p>
 * 进阶：不要 使用任何内置的库函数，如 sqrt 。
 *
 * @author yibo
 * @date 2021-11-04 11:13
 **/
public class ValidPerfectSquare {
    public boolean isPerfectSquare(int num) {
        int l = 1;
        int r = num;
        int target = (l + r) / 2;
        while (l <= r) {
            long value = (long) target * target;
            if (value > num) {
                r = target - 1;
                target = (l + r) / 2;
            } else if (value == num) {
                return true;
            } else {
                l = target + 1;
                target = (l + r) / 2;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        ValidPerfectSquare v=new ValidPerfectSquare();
        System.out.println(v.isPerfectSquare(16));
    }
}
