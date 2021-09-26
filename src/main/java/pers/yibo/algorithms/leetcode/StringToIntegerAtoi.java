package pers.yibo.algorithms.leetcode;

/**
 * 8. 字符串转换整数 (atoi)
 * <p>
 * https://leetcode-cn.com/problems/string-to-integer-atoi
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31, 2^31−1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −231 的整数应该被固定为 −231 ，大于 231 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 *
 * @author yibo
 * @date 2021-09-26 15:21
 **/
public class StringToIntegerAtoi {
    public int myAtoi(String s) {
        int out = 0;
        int negative = 1;
        boolean isBeforeBlank = true;
        for (char c : s.toCharArray()) {
            if (isBeforeBlank) {
                // 先导空格和正负号
                if (c == '-') {
                    negative = -1;
                    isBeforeBlank = false;
                } else if (c == '+') {
                    isBeforeBlank = false;
                } else if (c <= '9' && c >= '0') {
                    out = c - '0';
                    isBeforeBlank = false;
                } else if (c != ' ') {
                    return 0;
                }
            } else {
                if (c <= '9' && c >= '0') {
                    if (checkIntOverflow(out, c - '0', negative)) {
                        if (negative == 1) {
                            return Integer.MAX_VALUE;
                        } else {
                            return Integer.MIN_VALUE;
                        }
                    }
                    out = 10 * out + c - '0';
                } else {
                    return out * negative;
                }
            }
        }
        return out * negative;
    }

    public boolean checkIntOverflow(int num, int plus, int negative) {
        if (negative == 1) {
            int max = (Integer.MAX_VALUE - plus) / 10;
            return num > max;
        } else {
            int min = (Integer.MIN_VALUE + plus) / 10;
            return num * negative < min;
        }
    }

    public static void main(String[] args) {
        StringToIntegerAtoi s = new StringToIntegerAtoi();
        System.out.println(s.myAtoi("  -42"));
    }
}
