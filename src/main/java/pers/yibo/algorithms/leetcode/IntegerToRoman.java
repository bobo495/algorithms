package pers.yibo.algorithms.leetcode;

/**
 * 12. 整数转罗马数字
 * <p>
 * https://leetcode-cn.com/problems/integer-to-roman
 * <p>
 * 罗马数字包含以下七种字符：I，V，X，L，C，D和M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做II，即为两个并列的 1。12 写做XII，即为X+II。 27 写做XXVII, 即为XX+V+II。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做IIII，而是IV。数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I可以放在V(5) 和X(10) 的左边，来表示 4 和 9。
 * X可以放在L(50) 和C(100) 的左边，来表示 40 和90。
 * C可以放在D(500) 和M(1000) 的左边，来表示400 和900。
 * 给你一个整数，将其转为罗马数字。
 *
 * @author aoyb
 * 2021/10/8 21:15
 */
public class IntegerToRoman {
    public String intToRoman(int num) {
        StringBuilder builder = new StringBuilder();

        while (num >= 1000) {
            builder.append('M');
            num -= 1000;
        }

        if (num >= 900) {
            builder.append("CM");
            num -= 900;
        }

        if (num >= 500) {
            builder.append('D');
            num -= 500;
        }

        if (num >= 400) {
            builder.append("CD");
            num -= 400;
        }

        while (num >= 100) {
            builder.append('C');
            num -= 100;
        }

        if (num >= 90) {
            builder.append("XC");
            num -= 90;
        }

        if (num >= 50) {
            builder.append('L');
            num -= 50;
        }

        if (num >= 40) {
            builder.append("XL");
            num -= 40;
        }

        while (num >= 10) {
            builder.append('X');
            num -= 10;
        }

        if (num >= 9) {
            builder.append("IX");
            num -= 9;
        }

        if (num >= 5) {
            builder.append('V');
            num -= 5;
        }

        if (num >= 4) {
            builder.append("IV");
            num -= 4;
        }

        while (num > 0) {
            builder.append('I');
            num -= 1;
        }


        return builder.toString();
    }

    public static void main(String[] args) {
        IntegerToRoman integerToRoman=new IntegerToRoman();
        System.out.println(integerToRoman.intToRoman(1994));
    }
}
