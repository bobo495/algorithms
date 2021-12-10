package pers.yibo.algorithms.leetcode;

/**
 * 748. 最短补全词
 * <p>
 * https://leetcode-cn.com/problems/shortest-completing-word
 * <p>
 * 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
 * <p>
 * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
 *
 * @author yibo
 * @date 2021-12-10 09:27
 **/
public class ShortestCompletingWord {
    public String shortestCompletingWord(String licensePlate, String[] words) {
        int[] lp = new int[26];
        for (char c : licensePlate.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') {
                lp[c - 'a']++;
            }
        }

        int minLength = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            int[] w = new int[26];
            for (char c : word.toCharArray()) {
                w[c - 'a']++;
            }

            if (check(lp, w) && word.length() < minLength) {
                minLength = word.length();
                minIndex = i;
            }
        }
        return words[minIndex];
    }

    public boolean check(int[] lp, int[] w) {
        for (int i = 0; i < 26; i++) {
            if (w[i] < lp[i]) {
                return false;
            }
        }
        return true;
    }
}
