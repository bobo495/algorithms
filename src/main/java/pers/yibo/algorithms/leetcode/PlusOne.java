package pers.yibo.algorithms.leetcode;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/
 *
 * @author yibo
 * @date 2021-09-18 10:05
 **/
public class PlusOne {
    public int[] plusOne(int[] digits) {

        for (int i = digits.length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                digits[i] += 1;
                return digits;
            }
            digits[i] = 0;
        }

        int[] out = new int[digits.length + 1];
        out[0] = 1;
        System.arraycopy(digits, 0, out, 1, out.length - 1);
        return out;
    }
}
