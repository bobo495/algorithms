package pers.yibo.algorithms.leetcode;

/**
 * 807. 保持城市天际线
 * <p>
 * https://leetcode-cn.com/problems/max-increase-to-keep-city-skyline
 * <p>
 * 给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。
 * <p>
 * 城市的 天际线 是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的 天际线 可能不同。
 * <p>
 * 我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同） 。 高度为 0 的建筑物的高度也可以增加。然而，增加的建筑物高度 不能影响 从任何主要方向观察城市得到的 天际线 。
 * <p>
 * 在 不改变 从任何主要方向观测到的城市 天际线 的前提下，返回建筑物可以增加的 最大高度增量总和 。
 *
 * @author yibo
 * @date 2021-12-13 15:55
 **/
public class MaxIncreaseToKeepCitySkyline {
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int n = grid.length;
        int[] rowMax = new int[n];
        int[] colMax = new int[n];

        for (int i = 0; i < n; i++) {
            int tmpRowMax = 0;
            for (int j = 0; j < n; j++) {
                tmpRowMax = Math.max(tmpRowMax, grid[i][j]);
            }
            rowMax[i] = tmpRowMax;
        }

        for (int i = 0; i < n; i++) {
            int tmpColMax = 0;
            for (int j = 0; j < n; j++) {
                tmpColMax = Math.max(tmpColMax, grid[j][i]);
            }
            colMax[i] = tmpColMax;
        }


        int maxIncrease = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                maxIncrease += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }

        return maxIncrease;
    }
}
