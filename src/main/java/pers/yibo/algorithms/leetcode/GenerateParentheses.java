package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 22. 括号生成
 * <p>
 * https://leetcode-cn.com/problems/generate-parentheses/
 * <p>
 * 数字 n代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 * <p>
 * 有效括号组合需满足：左括号必须以正确的顺序闭合。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 * 示例 2：
 * <p>
 * 输入：n = 1
 * 输出：["()"]
 *
 * @author aoyb
 * 2021/10/8 21:13
 */
public class GenerateParentheses {

    public List<String> generateParenthesis(int n) {
        List<String> results = new ArrayList<>();

        addBrackets(results, new StringBuilder(), n, n);

        return results;
    }

    public void addBrackets(List<String> results, StringBuilder builder, int left, int right) {
        if (left == 0 && right == 0) {
            results.add(builder.toString());
            return;
        }

        if (left == right) {
            addBrackets(results, builder.append("("), left - 1, right);
        } else if (left > 0) {
            StringBuilder tmpBuilder = new StringBuilder();
            addBrackets(results, tmpBuilder.append(builder).append("("), left - 1, right);
            addBrackets(results, builder.append(")"), left, right - 1);
        } else {
            addBrackets(results, builder.append(")"), left, right - 1);
        }
    }

    public static void main(String[] args) {
        GenerateParentheses generateParenthesis = new GenerateParentheses();
        System.out.println(generateParenthesis.generateParenthesis(3));
    }

}
