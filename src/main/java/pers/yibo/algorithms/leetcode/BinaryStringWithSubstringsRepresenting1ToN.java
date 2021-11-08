package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 1016. 子串能表示从 1 到 N 数字的二进制串
 * <p>
 * https://leetcode-cn.com/problems/binary-string-with-substrings-representing-1-to-n
 * <p>
 * 给定一个二进制字符串S（一个仅由若干'0' 和 '1' 构成的字符串）和一个正整数N，如果对于从 1 到 N 的每个整数 X，其二进制表示都是S 的子串，就返回 true，否则返回 false。
 *
 * @author yibo
 * @date 2021-11-08 10:01
 **/
public class BinaryStringWithSubstringsRepresenting1ToN {
    public boolean queryString(String s, int n) {
        for (int i = 1; i <= n; i++) {
            if (!s.contains(Integer.toBinaryString(i))) {
                return false;
            }
        }
        return true;
    }

    public boolean queryString2(String s, int n) {
        // n转二进制字符串
        StringBuilder numBuilder = new StringBuilder();
        while (n > 1) {
            numBuilder.insert(0, n % 2);
            n = n / 2;
        }
        numBuilder.insert(0, 1);

        // 找到所有最大子串
        List<String> substrings = new ArrayList<>();
        findSubString(substrings, numBuilder, 1);

        for (String subStr : substrings) {
            if (!s.contains(subStr)) {
                return false;
            }
        }
        return true;
    }

    public void findSubString(List<String> subStrings, StringBuilder numBuilder, int index) {
        if (index == numBuilder.length()) {
            subStrings.add(numBuilder.toString());
            return;
        }

        if (numBuilder.charAt(index) == '1') {
            StringBuilder newBuilder = new StringBuilder(numBuilder);
            newBuilder.setCharAt(index, '0');
            findSubString(subStrings, newBuilder, index + 1);
        } else {
            if (index == 1) {
                StringBuilder newBuilder = new StringBuilder();
                for (int i = 1; i < numBuilder.length(); i++) {
                    newBuilder.append('1');
                }
                findSubString(subStrings, newBuilder, 1);
            }
        }
        findSubString(subStrings, numBuilder, index + 1);
    }

    public static void main(String[] args) {
        BinaryStringWithSubstringsRepresenting1ToN b = new BinaryStringWithSubstringsRepresenting1ToN();
        String s = "0110";
        int n = 1000000000;
        System.out.println(b.queryString(s, n));
    }
}
