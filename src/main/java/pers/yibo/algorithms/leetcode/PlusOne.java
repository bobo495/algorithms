package pers.yibo.algorithms.leetcode;

import java.util.Arrays;
import java.util.Collections;

/**
 * 66. 加一
 * https://leetcode-cn.com/problems/plus-one/
 *
 * @author yibo
 * @date 2021-09-18 10:05
 **/
public class PlusOne {
    public int[] plusOne(int[] digits) {

        digits[digits.length - 1] += 1;
        if (digits[digits.length - 1] < 10) {
            return digits;
        }

        int carry = 1;
        digits[digits.length - 1] -= 10;

        for (int i = digits.length - 2; i >= 0; i--) {
            int num = digits[i] + carry;
            if (num >= 10) {
                carry = 1;
                num -= 10;
            } else {
                carry = 0;
            }
            digits[i] = num;
        }
        if (carry != 0) {
            int[] out = new int[digits.length + 1];
            out[0] = carry;
            System.arraycopy(digits, 0, out, 1, out.length - 1);
            return out;
        }
        return digits;
    }
}
