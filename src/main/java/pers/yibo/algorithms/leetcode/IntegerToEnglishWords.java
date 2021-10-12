package pers.yibo.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 273. 整数转换英文表示
 * <p>
 * https://leetcode-cn.com/problems/integer-to-english-words
 * <p>
 * 将非负整数 num 转换为其对应的英文表示。
 * <p>
 * 输入：num = 1234567891
 * 输出："One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One"
 *
 * @author yibo
 * @date 2021-10-11 14:41
 **/
public class IntegerToEnglishWords {

    static Map<Integer, String> mapIntToWords = new HashMap<>();

    static {
        mapIntToWords.put(1, "One");
        mapIntToWords.put(2, "Two");
        mapIntToWords.put(3, "Three");
        mapIntToWords.put(4, "Four");
        mapIntToWords.put(5, "Five");
        mapIntToWords.put(6, "Six");
        mapIntToWords.put(7, "Seven");
        mapIntToWords.put(8, "Eight");
        mapIntToWords.put(9, "Nine");
        mapIntToWords.put(10, "Ten");
        mapIntToWords.put(11, "Eleven");
        mapIntToWords.put(12, "Twelve");
        mapIntToWords.put(13, "Thirteen");
        mapIntToWords.put(14, "Fourteen");
        mapIntToWords.put(15, "Fifteen");
        mapIntToWords.put(16, "Sixteen");
        mapIntToWords.put(17, "Seventeen");
        mapIntToWords.put(18, "Eighteen");
        mapIntToWords.put(19, "Nineteen");
        mapIntToWords.put(20, "Twenty");
        mapIntToWords.put(30, "Thirty");
        mapIntToWords.put(40, "Forty");
        mapIntToWords.put(50, "Fifty");
        mapIntToWords.put(60, "Sixty");
        mapIntToWords.put(70, "Seventy");
        mapIntToWords.put(80, "Eighty");
        mapIntToWords.put(90, "Ninety");
    }

    public String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        StringBuilder result = new StringBuilder();
        if (num >= 1000000000) {
            int sub = num / 1000000000;
            result.append(smallConvert(sub)).append(" Billion ");
            num -= sub * 1000000000;
        }

        if (num >= 1000000) {
            int sub = num / 1000000;
            result.append(smallConvert(sub)).append(" Million ");
            num -= sub * 1000000;
        }

        if (num >= 1000) {
            int sub = num / 1000;
            result.append(smallConvert(sub)).append(" Thousand ");
            num -= sub * 1000;
        }
        result.append(smallConvert(num));
        if (result.charAt(result.length() - 1) == ' ') {
            result.deleteCharAt(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * 小于1000
     *
     * @param num 小于1000的数字
     * @return 英文表示
     */
    public StringBuilder smallConvert(int num) {
        StringBuilder subResult = new StringBuilder();
        if (num == 0) {
            return subResult;
        }
        if (num >= 100) {
            int hundred = num / 100;
            subResult.append(mapIntToWords.get(hundred)).append(" Hundred ");
            num = num % 100;
        }
        if (num == 0) {
            return subResult.deleteCharAt(subResult.length() - 1);
        }
        if (num >= 20) {
            int small = num % 10;
            int big = num - small;
            subResult.append(mapIntToWords.get(big));
            if (small != 0) {
                subResult.append(" ").append(mapIntToWords.get(small));
            }
        } else {
            subResult.append(mapIntToWords.get(num));
        }

        return subResult;
    }

    public static void main(String[] args) {
        IntegerToEnglishWords integerToEnglishWords = new IntegerToEnglishWords();
        System.out.println("-" + integerToEnglishWords.numberToWords(123));
    }
}
