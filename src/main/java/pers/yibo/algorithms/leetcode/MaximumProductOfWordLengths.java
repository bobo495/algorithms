package pers.yibo.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 318. 最大单词长度乘积
 * <p>
 * https://leetcode-cn.com/problems/maximum-product-of-word-lengths
 * <p>
 * 给定一个字符串数组words，找到length(word[i]) * length(word[j])的最大值，并且这两个单词不含有公共字母。
 * 你可以认为每个单词只包含小写字母。如果不存在这样的两个单词，返回 0。
 *
 * @author yibo
 * @date 2021-11-17 14:13
 **/
public class MaximumProductOfWordLengths {

    public int maxProduct(String[] words) {
        int length = words.length;
        // 字母矩阵改为int位数标识
        int[] charBit = new int[length];

        for (int i = 0; i < length; i++) {
            // charAt取字符会比字符串转charArray更快
            for (int j = 0; j <  words[i].length(); j++) {
                charBit[i] |= (1 << ( words[i].charAt(j) - 'a'));
            }
        }

        int max = 0;
        // 当两个单词的位预算与为0时，说明没有公共单词
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if ((charBit[i] & charBit[j]) == 0) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }

    /**
     * hashmap太慢了
     */
    public int maxProduct3(String[] words) {
        // int位数标识放到hashMap中，重复的标识取最长长度
        Map<Integer, Integer> map = new HashMap<>(1000);

        for (String word : words) {
            int mask = 0;
            for (char c : word.toCharArray()) {
                mask |= 1 << (c - 'a');
            }
            map.put(mask, Math.max(word.length(), map.getOrDefault(mask, 0)));
        }

        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (Map.Entry<Integer, Integer> entry2 : map.entrySet()) {
                if ((entry.getKey() & entry2.getKey()) == 0) {
                    max = Math.max(max, entry.getValue() * entry2.getValue());
                }
            }
        }
        return max;
    }

    public int maxProduct2(String[] words) {
        // 字母矩阵
        boolean[][] charMatrix = new boolean[words.length][26];

        // 初始化字母矩阵
        for (int i = 0; i < words.length; i++) {
            for (char c : words[i].toCharArray()) {
                charMatrix[i][c - 'a'] = true;
            }
        }
        int max = 0;
        for (int i = 0; i < charMatrix.length; i++) {
            for (int j = i + 1; j < charMatrix.length; j++) {
                boolean match = true;
                for (int k = 0; k < 25; k++) {
                    if (charMatrix[i][k] && charMatrix[j][k]) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    max = Math.max(max, words[i].length() * words[j].length());
                }
            }
        }
        return max;
    }
}
