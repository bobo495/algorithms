package pers.yibo.algorithms.leetcode;

/**
 * 997. 找到小镇的法官
 * <p>
 * https://leetcode-cn.com/problems/find-the-town-judge
 * <p>
 * 在一个小镇里，按从 1 到 n 为 n 个人进行编号。传言称，这些人中有一个是小镇上的秘密法官。
 * <p>
 * 如果小镇的法官真的存在，那么：
 * <p>
 * 小镇的法官不相信任何人。
 * 每个人（除了小镇法官外）都信任小镇的法官。
 * 只有一个人同时满足条件 1 和条件 2 。
 * 给定数组 trust，该数组由信任对 trust[i] = [a, b]组成，表示编号为 a 的人信任编号为 b 的人。
 * <p>
 * 如果小镇存在秘密法官并且可以确定他的身份，请返回该法官的编号。否则，返回 -1。
 *
 * @author yibo
 * 2021/12/19 17:10
 */
public class FindTheTownJudge {
    public int findJudge(int n, int[][] trust) {
        // 针对条件1. person i如果信任别人则标记为true
        boolean[] marked = new boolean[n];
        // 每个人被信任的人数
        int[] trusted = new int[n];

        for (int[] e : trust) {
            marked[e[0] - 1] = true;
            trusted[e[1] - 1]++;
        }
        // marked中只有一个false
        for (int i = 0; i < n; i++) {
            if (!marked[i] && trusted[i] == n - 1) {
                return i + 1;
            }
        }
        return -1;
    }
}
