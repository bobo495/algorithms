package pers.yibo.algorithms.leetcode;

/**
 * 1034. 边界着色
 * <p>
 * https://leetcode-cn.com/problems/coloring-a-border
 * <p>
 * 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数row、col 和 color 。网格中的每个值表示该位置处的网格块的颜色。
 * <p>
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 * <p>
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 * <p>
 * 请你使用指定颜色color 为所有包含网格块grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格grid 。
 *
 * @author yibo
 * @date 2021-12-07 09:16
 **/
public class ColoringBorder {

    /**
     * 四个方向
     */
    private static final int[] DIRECTION = {0, 1, 0, -1, 0};

    public int[][] colorBorder(int[][] grid, int row, int col, int color) {
        boolean[][] marked = new boolean[grid.length][grid[0].length];
        dfs(grid, marked, row, col, grid[row][col], color);
        return grid;
    }

    private boolean dfs(int[][] grid, boolean[][] marked, int row, int col, int oldColor, int newColor) {
        // 已经改变过了，则直接返回true
        if (grid[row][col] == oldColor && !marked[row][col]) {
            marked[row][col] = true;
            grid[row][col] = newColor;
            int border = 0;
            for (int i = 0; i < 4; i++) {
                int newRow = row + DIRECTION[i];
                int newCol = col + DIRECTION[i + 1];
                if (newRow >= 0 && newRow < grid.length && newCol >= 0 && newCol < grid[0].length) {
                    boolean inCC = dfs(grid, marked, newRow, newCol, oldColor, newColor);
                    if (inCC) {
                        border++;
                    }
                }
            }
            if (border == 4) {
                // 上下左右都在连通分量中，在将颜色改回去
                grid[row][col] = oldColor;
            }
            return true;
        } else {
            return marked[row][col];
        }
    }
}
