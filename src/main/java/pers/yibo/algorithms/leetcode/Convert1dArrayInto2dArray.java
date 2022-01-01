package pers.yibo.algorithms.leetcode;

/**
 * 2022. 将一维数组转变成二维数组
 *
 * @author yibo
 * 2022/1/1 21:05
 */
public class Convert1dArrayInto2dArray {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[][]{};
        }

        int[][] ans = new int[m][n];
        int row = 0;
        int col = 0;

        for (int j : original) {
            ans[row][col] = j;
            if (col == n - 1) {
                row++;
                col = 0;
            } else {
                col++;
            }
        }
        return ans;
    }
}
