package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 221. 最大正方形
 * <p>
 * https://leetcode-cn.com/problems/maximal-square/
 * <p>
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * @author yibo
 * @date 2021-10-26 14:33
 **/
public class MaximalSquare {

    /**
     * 动态规划
     * <p>
     * 创建一个与matrix同样大小的矩阵，其中每一个值表示以该坐标为右下角的正方形大小
     *
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {

        int maxSideLength = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                char c = matrix[i][j];
                if (!(i >= 1 && j >= 1)) {
                    dp[i][j] = c - '0';
                    if (dp[i][j] == 1 && maxSideLength == 0) {
                        maxSideLength = 1;
                    }
                } else {
                    if (c == '0') {
                        dp[i][j] = 0;
                    } else {
                        // 计算dp值：取以(i,j)为右下角 2*2 矩阵的最小值 + 1
                        dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                        if (dp[i][j] > maxSideLength) {
                            maxSideLength = dp[i][j];
                        }
                    }
                }
            }
        }

        return maxSideLength * maxSideLength;
    }


    /**
     * 暴力求解
     *
     * @param matrix
     * @return
     */
    public int maximalSquare2(char[][] matrix) {

        int maxSideLength = 0;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    int tmpSideLength = findSquare(matrix, i, j, 1);
                    maxSideLength = Math.max(maxSideLength, tmpSideLength);
                }
            }
        }

        return maxSideLength * maxSideLength;
    }

    public int findSquare(char[][] matrix, int startX, int startY, int sideLength) {

        if (startX + sideLength >= matrix.length || startY + sideLength >= matrix[0].length) {
            return sideLength;
        }

        // 校验列数据
        for (int i = startX; i <= startX + sideLength; i++) {
            if (matrix[i][startY + sideLength] == '0') {
                return sideLength;
            }
        }

        // 校验行数据
        for (int i = startY; i < startY + sideLength; i++) {
            if (matrix[startX + sideLength][i] == '0') {
                return sideLength;
            }
        }

        return findSquare(matrix, startX, startY, sideLength + 1);
    }
}
