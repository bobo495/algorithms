package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 17. 电话号码的字母组合
 * <p>
 * https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number
 * <p>
 * 给定一个仅包含数字2-9的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * <p>
 * 示例 1：
 * <p>
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 * <p>
 * 输入：digits = ""
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 * @author yibo
 * @date 2021-10-08 15:21
 **/
public class LetterCombinationsOfPhoneNumber {

    public static Map<Character, String> map = new HashMap<>(8);

    static {
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
    }

    public List<String> letterCombinations(String digits) {
        List<String> results = new ArrayList<>();

        if (digits.length() == 0) {
            return results;
        }

        addLetter(results,digits,new StringBuilder(),0);

        return results;
    }

    /**
     * 字符串拼接场景：StringBuilder速度比直接String拼接方式快很多
     */
    public void addLetter(List<String> results, String digits, StringBuilder builder, int index) {
        if (index == digits.length()) {
            results.add(builder.toString());
            return;
        }

        char[] letters = map.get(digits.charAt(index)).toCharArray();
        for (char l : letters) {
            StringBuilder temporarily = new StringBuilder();
            temporarily.append(builder).append(l);
            addLetter(results, digits, temporarily, index + 1);
        }
    }


    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber l = new LetterCombinationsOfPhoneNumber();
        System.out.println(l.letterCombinations("32"));
    }
}
