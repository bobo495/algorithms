package pers.yibo.algorithms.leetcode;

/**
 * 1091. 二进制矩阵中的最短路径
 * <p>
 * https://leetcode-cn.com/problems/shortest-path-in-binary-matrix
 * <p>
 * 给你一个 n x n 的二进制矩阵 grid 中，返回矩阵中最短 畅通路径 的长度。如果不存在这样的路径，返回 -1 。
 * <p>
 * 二进制矩阵中的 畅通路径 是一条从 左上角 单元格（即，(0, 0)）到 右下角 单元格（即，(n - 1, n - 1)）的路径，该路径同时满足下述要求：
 * <p>
 * 路径途经的所有单元格都的值都是 0 。
 * 路径中所有相邻的单元格应当在 8 个方向之一 上连通（即，相邻两单元之间彼此不同且共享一条边或者一个角）。
 * 畅通路径的长度 是该路径途经的单元格总数。
 *
 * @author yibo
 * @date 2021-11-05 17:05
 **/
public class ShortestPathInBinaryMatrix {

    static int[] dir = new int[]{0, -1, -1, 0, 1, -1, 1, 1, 0};

    class Node {
        int[] point;
        Node next;
    }

    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if (grid[0][0] == 1 || grid[n - 1][n - 1] == 1) {
            return -1;
        }

        if (n == 1 && grid[0][0] == 0) {
            return 1;
        }

        Node positions = new Node();
        positions.point = new int[]{0, 0};

        grid[0][0] = 1;
        return findNext(grid, positions, 1, n);
    }

    public int findNext(int[][] grid, Node positions, int steps, int n) {
        if (positions.point == null) {
            return -1;
        }
        Node nextPositions = new Node();
        Node tmp = nextPositions;
        while (positions != null && positions.point != null) {
            for (int i = 0; i < 8; i++) {
                int[] nextPosition = new int[]{positions.point[0] + dir[i], positions.point[1] + dir[i + 1]};
                if (nextPosition[0] < 0 || nextPosition[0] >= n || nextPosition[1] < 0 || nextPosition[1] >= n) {
                    continue;
                }
                if (nextPosition[0] == n - 1 && nextPosition[1] == n - 1) {
                    return steps + 1;
                }
                if (grid[nextPosition[0]][nextPosition[1]] == 0) {
                    tmp.point = nextPosition;
                    tmp.next = new Node();
                    tmp = tmp.next;
                    grid[nextPosition[0]][nextPosition[1]] = 1;
                }
            }
            positions = positions.next;
        }
        return findNext(grid, nextPositions, steps + 1, n);
    }
}
