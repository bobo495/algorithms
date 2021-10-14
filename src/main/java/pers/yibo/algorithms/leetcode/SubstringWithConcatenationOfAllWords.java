package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 30. 串联所有单词的子串
 * <p>
 * https://leetcode-cn.com/problems/substring-with-concatenation-of-all-words
 * <p>
 * 给定一个字符串s和一些 长度相同 的单词words 。找出 s 中恰好可以由words 中所有单词串联形成的子串的起始位置。
 * <p>
 * 注意子串要与words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑words中单词串联的顺序。
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：
 * 从索引 0 和 9 开始的子串分别是 "barfoo" 和 "foobar" 。
 * 输出的顺序不重要, [9,0] 也是有效答案。
 * 示例 2：
 * <p>
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 *
 * @author yibo
 * @date 2021-10-14 11:17
 **/
public class SubstringWithConcatenationOfAllWords {

    /**
     * 使用hashMap实现
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int wordLength = words[0].length();

        HashMap<String, Integer> mapWords = new HashMap<>(words.length);
        for (String word : words) {
            mapWords.put(word, mapWords.getOrDefault(word, 0) + 1);
        }
        int count = 0;

        int totalL = wordLength * (words.length - 1);

        HashMap<String, Integer> tmpMap = new HashMap<>(mapWords);
        for (int i = 0; i < s.length() - wordLength + 1; i++) {
            String subString = s.substring(i, i + wordLength);
            if (tmpMap.containsKey(subString)) {
                int nums = tmpMap.get(subString);
                count++;
                if (nums == 1) {
                    tmpMap.remove(subString);
                    if (tmpMap.size() == 0) {
                        i -= totalL;
                        result.add(i);
                        tmpMap = new HashMap<>(mapWords);
                        count = 0;
                        continue;
                    }
                } else {
                    tmpMap.put(subString, nums - 1);
                }
                i += wordLength - 1;
            } else if (count != 0) {
                tmpMap = new HashMap<>(mapWords);
                i -= count * wordLength;
                count = 0;
            } else if (i >= s.length() - totalL) {
                return result;
            }
        }

        return result;
    }

    /**
     * 仅使用基本类型实现
     *
     * @param s
     * @param words
     * @return
     */
    public List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> result = new ArrayList<>();
        int wordLength = words[0].length();
        String[] tmpWords = words.clone();
        int count = 0;
        for (int i = 0; i < s.length() - wordLength + 1; i++) {
            String subString = s.substring(i, i + wordLength);
            boolean found = false;
            for (int j = 0; j < tmpWords.length; j++) {
                if (tmpWords[j].equals(subString)) {
                    tmpWords[j] = "";
                    count++;
                    if (count == words.length) {
                        result.add(i - (words.length - 1) * wordLength);
                        i = i - (words.length - 1) * wordLength;
                        tmpWords = words.clone();
                        count = 0;
                        break;
                    }
                    found = true;
                    i += wordLength - 1;
                    break;
                }
            }

            if (!found && count != 0) {
                tmpWords = words.clone();
                i -= count * wordLength;
                count = 0;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        SubstringWithConcatenationOfAllWords test = new SubstringWithConcatenationOfAllWords();
//        String s = "barfoothefoobarman";
        String s = "wordgoodgoodgoodbestwordaa";
//        String[] words = new String[]{"foo", "bar"};
        String[] words = new String[]{"word", "good", "best", "good"};
        System.out.println(test.findSubstring(s, words));
    }

}
