package pers.yibo.algorithms.leetcode;

/**
 * 1925. 统计平方和三元组的数目
 * <p>
 * https://leetcode-cn.com/problems/count-square-sum-triples
 * <p>
 * 一个 平方和三元组(a,b,c)指的是满足 a2 + b2 = c2的 整数三元组a，b和c。
 * <p>
 * 给你一个整数n，请你返回满足1 <= a, b, c <= n的 平方和三元组 的数目。
 *
 * @author yibo
 * @date 2021-11-23 17:44
 **/
public class CountSquareSumTriples {

    public int countTriples(int n) {
        int ans = 0;
        for (int a = 2; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                int left = a * a + b * b;
                int c = (int) Math.sqrt(left);
                if (c <= n) {
                    if (left == c * c) {
                        ans += 2;
                    }
                } else {
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        CountSquareSumTriples c = new CountSquareSumTriples();
        System.out.println(c.countTriples(26));
    }
}
