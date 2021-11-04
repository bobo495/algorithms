package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 1310. 子数组异或查询
 * <p>
 * https://leetcode-cn.com/problems/xor-queries-of-a-subarray
 * <p>
 * 输入：arr = [1,3,4,8], queries = [[0,1],[1,2],[0,3],[3,3]]
 * 输出：[2,7,14,8]
 * 解释：
 * 数组中元素的二进制表示形式是：
 * 1 = 0001
 * 3 = 0011
 * 4 = 0100
 * 8 = 1000
 * 查询的 XOR 值为：
 * [0,1] = 1 xor 3 = 2
 * [1,2] = 3 xor 4 = 7
 * [0,3] = 1 xor 3 xor 4 xor 8 = 14
 * [3,3] = 8
 *
 * @author yibo
 * @date 2021-11-04 16:29
 **/
public class XorQueriesOfSubarray {
    public int[] xorQueries(int[] arr, int[][] queries) {
        int[] subXor = new int[arr.length + 1];
        for (int i = 1; i <= arr.length; i++) {
            subXor[i] = subXor[i - 1] ^ arr[i - 1];
        }

        int[] ans = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            ans[i] = subXor[queries[i][0]] ^ subXor[queries[i][1] + 1];
        }
        return ans;
    }

    public static void main(String[] args) {
        XorQueriesOfSubarray x = new XorQueriesOfSubarray();
        int[] arr = new int[]{1, 3, 4, 8};
        int[][] queries = new int[][]{
                {0, 1}, {1, 2}, {0, 3}, {3, 3}
        };
        System.out.println(Arrays.toString(x.xorQueries(arr, queries)));
    }
}
