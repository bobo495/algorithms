package pers.yibo.algorithms.jianzhioffer;

import java.util.Arrays;

/**
 * 剑指 Offer 60. n个骰子的点数
 * <p>
 * https://leetcode-cn.com/problems/nge-tou-zi-de-dian-shu-lcof
 * <p>
 * 把n个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * <p>
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 * <p>
 * 输入: 2
 * 输出: [0.02778,0.05556,0.08333,0.11111,0.13889,0.16667,0.13889,0.11111,0.08333,0.05556,0.02778]
 *
 * @author yibo
 * @date 2021-11-02 14:10
 **/
public class DicesProbability {
    public double[] dicesProbability(int n) {

        double[] result = new double[6];
        Arrays.fill(result, (double) 1 / 6);
        for (int i = 0; i < n - 1; i++) {
            result = addDices(result);
        }
        return result;
    }

    public double[] addDices(double[] d) {
        double[] next = new double[d.length + 5];

        next[0] = d[0] / 6;
        for (int i = 1; i < 6; i++) {
            next[i] = next[i - 1] + d[i] / 6;
        }

        for (int i = 6; i < next.length - 6; i++) {
            next[i] = (d[i] + d[i - 1] + d[i - 2] + d[i - 3] + d[i - 4] + d[i - 5]) / 6;
        }

        next[next.length - 1] = d[0] / 6;
        for (int i = next.length - 2; i > next.length - 7; i--) {
            next[i] = next[i + 1] + d[i - 5] / 6;
        }

        return next;
    }
}
