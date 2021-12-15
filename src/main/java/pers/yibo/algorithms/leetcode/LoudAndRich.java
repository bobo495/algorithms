package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 851. 喧闹和富有
 * <p>
 * https://leetcode-cn.com/problems/loud-and-rich
 * <p>
 * 输入：richer = [[1,0],[2,1],[3,1],[3,7],[4,3],[5,3],[6,3]], quiet = [3,2,5,4,6,1,7,0]
 * 输出：[5,5,2,5,4,5,6,7]
 * 解释：
 * answer[0] = 5，
 * person 5 比 person 3 有更多的钱，person 3 比 person 1 有更多的钱，person 1 比 person 0 有更多的钱。
 * 唯一较为安静（有较低的安静值 quiet[x]）的人是 person 7，
 * 但是目前还不清楚他是否比 person 0 更有钱。
 * answer[7] = 7，
 * 在所有拥有的钱肯定不少于 person 7 的人中（这可能包括 person 3，4，5，6 以及 7），
 * 最安静（有较低安静值 quiet[x]）的人是 person 7。
 * 其他的答案也可以用类似的推理来解释。
 *
 * @author yibo
 * @date 2021-12-15 09:10
 **/
public class LoudAndRich {

    public int[] loudAndRich(int[][] richer, int[] quiet) {
        // 人数
        int n = quiet.length;
        // 邻接表：每行为比person i更富有的人
        List<Integer>[] adjList = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int[] e : richer) {
            adjList[e[1]].add(e[0]);
        }

        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        for (int i = 0; i < n; i++) {
            dfs(adjList, quiet, i, ans);
        }

        return ans;
    }

    private void dfs(List<Integer>[] adjList, int[] quiet, int v, int[] ans) {
        if (ans[v] != -1) {
            return;
        }
        ans[v] = v;

        for (int w : adjList[v]) {
            dfs(adjList, quiet, w, ans);
            if (quiet[ans[w]] < quiet[ans[v]]) {
                ans[v] = ans[w];
            }
        }
    }

    public static void main(String[] args) {
        LoudAndRich l = new LoudAndRich();
        int[][] richer = new int[][]{{1, 0}, {2, 1}, {3, 1}, {3, 7}, {4, 3}, {5, 3}, {6, 3}};
        int[] quiet = new int[]{3, 2, 5, 4, 6, 1, 7, 0};
        System.out.println(Arrays.toString(l.loudAndRich(richer, quiet)));
    }

}
