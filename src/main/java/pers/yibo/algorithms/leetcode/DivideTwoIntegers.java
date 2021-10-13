package pers.yibo.algorithms.leetcode;

/**
 * 29. 两数相除
 * <p>
 * https://leetcode-cn.com/problems/divide-two-integers/
 * <p>
 * 给定两个整数，被除数dividend和除数divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。
 * <p>
 * 返回被除数dividend除以除数divisor得到的商。
 * <p>
 * 整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
 * <p>
 * <p>
 * 输入: dividend = 10, divisor = 3
 * 输出: 3
 * 解释: 10/3 = truncate(3.33333..) = truncate(3) = 3
 * <p>
 * 输入: dividend = 7, divisor = -3
 * 输出: -2
 * 解释: 7/-3 = truncate(-2.33333..) = -2
 *
 * @author yibo
 * @date 2021-10-12 11:26
 **/
public class DivideTwoIntegers {
    public int divide(int dividend, int divisor) {
        // 溢出情况
        if (divisor == 0 || (dividend == Integer.MIN_VALUE && divisor == -1)) {
            return Integer.MAX_VALUE;
        }
        if (divisor == 1) {
            return dividend;
        }
        if (divisor == -1) {
            return -dividend;
        }

        if (dividend == Integer.MIN_VALUE && divisor == Integer.MAX_VALUE) {
            return -1;
        }

        if ((divisor == Integer.MIN_VALUE || divisor == Integer.MAX_VALUE) && dividend != divisor) {
            return 0;
        }

        if (dividend >= 0 && divisor > 0) {
            return findCloset(0, 0, dividend, divisor, divisor, 1);
        } else if (dividend < 0 && divisor < 0) {
            if (dividend == Integer.MIN_VALUE) {
                dividend -= divisor;
                return findCloset(0, 0, -dividend, -divisor, -divisor, 1) + 1;
            }
            return findCloset(0, 0, -dividend, -divisor, -divisor, 1);
        } else if (divisor > 0) {
            if (dividend == Integer.MIN_VALUE) {
                dividend += divisor;
                return -findCloset(0, 0, -dividend, divisor, divisor, 1) - 1;
            }
            return -findCloset(0, 0, -dividend, divisor, divisor, 1);
        } else {
            return -findCloset(0, 0, dividend, -divisor, -divisor, 1);
        }
    }


    public int findCloset(int result, int resultMultiple,
                          int dividend, int divisor,
                          int addNumber, int addMultiple) {
        if (result == dividend) {
            return resultMultiple;
        }

        if (result < dividend) {
            // 增加
            System.out.println("add\t" + result + " " + resultMultiple + "\t" + addNumber + " " + addMultiple);
            if (Integer.MAX_VALUE - result < divisor) {
                return resultMultiple;
            }
            if (result + divisor == dividend) {
                return resultMultiple + 1;
            } else if (result + divisor > dividend) {
                return resultMultiple;
            }

            if (Integer.MAX_VALUE - result < addNumber) {
                addNumber = divisor;
                addMultiple = 1;
                return findCloset(result, resultMultiple, dividend, divisor, addNumber, addMultiple);
            }

            result += addNumber;
            resultMultiple += addMultiple;
            if (result < dividend) {
                addNumber += addNumber;
                addMultiple += addMultiple;
                return findCloset(result, resultMultiple, dividend, divisor, addNumber, addMultiple);
            } else if (result > dividend) {
                addNumber = -divisor;
                addMultiple = -1;
                System.out.println("add change\t" + result + " " + resultMultiple + "\t" + addNumber + " " + addMultiple);
                return findCloset(result, resultMultiple, dividend, divisor, addNumber, addMultiple);
            } else {
                return resultMultiple;
            }
        } else {
            // 减少
            System.out.println("minus\t" + result + " " + resultMultiple + "\t" + addNumber + " " + addMultiple);
            if (Integer.MIN_VALUE + divisor > result) {
                return resultMultiple;
            }

            if (result - divisor <= dividend) {
                return resultMultiple - 1;
            }

            if (Integer.MIN_VALUE - addNumber > result) {
                addNumber = divisor;
                addMultiple = 1;
                return findCloset(result, resultMultiple, dividend, divisor, addNumber, addMultiple);
            }

            result += addNumber;
            resultMultiple += addMultiple;
            if (result > dividend) {
                addNumber += addNumber;
                addMultiple += addMultiple;
                return findCloset(result, resultMultiple, dividend, divisor, addNumber, addMultiple);
            } else if (result < dividend) {
                addNumber = divisor;
                addMultiple = 1;
                System.out.println("minus change\t" + result + " " + resultMultiple + "\t" + addNumber + " " + addMultiple);
                return findCloset(result, resultMultiple, dividend, divisor, addNumber, addMultiple);
            } else {
                return resultMultiple;
            }
        }
    }

    public static void main(String[] args) {
        DivideTwoIntegers d = new DivideTwoIntegers();
        System.out.println(d.divide(1262439062, -629703411));
    }
}
