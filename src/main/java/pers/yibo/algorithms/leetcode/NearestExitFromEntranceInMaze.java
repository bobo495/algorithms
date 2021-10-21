package pers.yibo.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 1926. 迷宫中离入口最近的出口
 * <p>
 * https://leetcode-cn.com/problems/nearest-exit-from-entrance-in-maze
 * <p>
 * 输入：maze = [["+","+",".","+"],[".",".",".","+"],["+","+","+","."]], entrance = [1,2]
 * 输出：1
 * 解释：总共有 3 个出口，分别位于 (1,0)，(0,2) 和 (2,3) 。
 * 一开始，你在入口格子 (1,2) 处。
 * - 你可以往左移动 2 步到达 (1,0) 。
 * - 你可以往上移动 1 步到达 (0,2) 。
 * 从入口处没法到达 (2,3) 。
 * 所以，最近的出口是 (0,2) ，距离为 1 步。
 *
 * @author yibo
 * @date 2021-10-20 14:34
 **/
public class NearestExitFromEntranceInMaze {


    /**
     * 广度优先搜索
     *
     * @param maze     二维矩阵
     * @param entrance 起点
     * @return 步数
     */
    public int nearestExit(char[][] maze, int[] entrance) {
        int maxSteps = 0;
        for (char[] chars : maze) {
            for (int j = 0; j < maze[0].length; j++) {
                if (chars[j] == '.') {
                    maxSteps++;
                }
            }
        }
        int[][] positions = new int[1][];
        positions[0] = entrance;
        maze[entrance[0]][entrance[1]] = '+';

        return findNext(maze, positions, 0, maxSteps, 1);
    }

    public int findNext(char[][] maze, int[][] positions, int steps, int maxSteps, int nextPositionLength) {

        if (positions[0] == null || steps > maxSteps) {
            return -1;
        }

        int index = 0;
        int nextIndex = 0;
        int[][] nextPositions = new int[nextPositionLength * 3 + 1][];
        int[] nextPosition = new int[2];

        while (index < positions.length && positions[index] != null) {
            // 上移
            nextPosition[1] = positions[index][1];
            nextPosition[0] = positions[index][0] - 1;
            if (nextPosition[0] >= 0 && maze[nextPosition[0]][nextPosition[1]] == '.') {
                if (nextPosition[0] == 0 || nextPosition[1] == 0 || nextPosition[1] == maze[0].length - 1) {
                    return steps + 1;
                } else {
                    nextPositions[nextIndex++] = new int[]{nextPosition[0], nextPosition[1]};
                    maze[nextPosition[0]][nextPosition[1]] = '+';
                }
            }

            // 下移
            nextPosition[0] = positions[index][0] + 1;
            if (nextPosition[0] < maze.length && maze[nextPosition[0]][nextPosition[1]] == '.') {
                if (nextPosition[0] == maze.length - 1 || nextPosition[1] == 0 || nextPosition[1] == maze[0].length - 1) {
                    return steps + 1;
                } else {
                    nextPositions[nextIndex++] = new int[]{nextPosition[0], nextPosition[1]};
                    maze[nextPosition[0]][nextPosition[1]] = '+';
                }
            }

            // 左移
            nextPosition[0] = positions[index][0];
            nextPosition[1] = positions[index][1] - 1;
            if (nextPosition[1] >= 0 && maze[nextPosition[0]][nextPosition[1]] == '.') {
                if (nextPosition[1] == 0 || nextPosition[0] == 0 || nextPosition[0] == maze.length - 1) {
                    return steps + 1;
                } else {
                    nextPositions[nextIndex++] = new int[]{nextPosition[0], nextPosition[1]};
                    maze[nextPosition[0]][nextPosition[1]] = '+';
                }
            }

            // 右移
            nextPosition[1] = positions[index][1] + 1;
            if (nextPosition[1] < maze[0].length && maze[nextPosition[0]][nextPosition[1]] == '.') {
                if (nextPosition[1] == maze[0].length - 1 || nextPosition[0] == 0 || nextPosition[0] == maze.length - 1) {
                    return steps + 1;
                } else {
                    nextPositions[nextIndex++] = new int[]{nextPosition[0], nextPosition[1]};
                    maze[nextPosition[0]][nextPosition[1]] = '+';
                }
            }
            index++;
        }

        return findNext(maze, nextPositions, steps + 1, maxSteps, nextIndex);
    }

    /**
     * 深度优先搜索方法
     * <p>
     * 缺陷：迷宫中路线较多，使用深度优先容易陷入循环，耗时较长
     *
     * @param maze     二维矩阵
     * @param entrance 起点
     * @return 步数
     */
    public int nearestExit2(char[][] maze, int[] entrance) {

        int result = Integer.MAX_VALUE;
        int maxRow = maze.length - 1;
        int maxCol = maze[0].length - 1;

        System.out.println(maxRow + " " + maxCol);

        int maxSteps = 0;
        for (int i = 0; i <= maxRow; i++) {
            for (int j = 0; j <= maxCol; j++) {
                if (maze[i][j] == '.') {
                    maxSteps++;
                }
            }
        }
        result = maxSteps + 1;

        int[] nextPosition = new int[2];

        // 向上
        nextPosition[0] = entrance[0] - 1;
        nextPosition[1] = entrance[1];
        if (nextPosition[0] >= 0 && maze[nextPosition[0]][nextPosition[1]] != '+') {
            result = move(maze.clone(), nextPosition, 'U', 1, result);
        }
        // 向下
        nextPosition[0] = entrance[0] + 1;
        nextPosition[1] = entrance[1];
        if (nextPosition[0] <= maxRow && maze[nextPosition[0]][nextPosition[1]] != '+') {
            result = move(maze.clone(), nextPosition, 'D', 1, result);
        }

        // 向左
        nextPosition[0] = entrance[0];
        nextPosition[1] = entrance[1] - 1;
        if (nextPosition[1] >= 0 && maze[nextPosition[0]][nextPosition[1]] != '+') {
            result = move(maze.clone(), nextPosition, 'L', 1, result);
        }
        // 向右
        nextPosition[0] = entrance[0];
        nextPosition[1] = entrance[1] + 1;
        if (nextPosition[1] <= maxCol && maze[nextPosition[0]][nextPosition[1]] != '+') {
            result = move(maze.clone(), nextPosition, 'R', 1, result);
        }

        return result == maxSteps + 1 ? -1 : result;
    }

    public int move(char[][] maze, int[] position, char direction, int steps, int minSteps) {

        System.out.println(position[0] + " " + position[1] + " " + direction + " " + steps + " " + minSteps);

        if (steps >= minSteps) {
            // 当前步数超过了最小步数则停止寻找
            return minSteps;
        }

        if (position[0] == 0 || position[0] == maze.length - 1 || position[1] == 0 || position[1] == maze[0].length - 1) {
            // 当前位置处于边界则直接返回
            return steps;
        }

        int[] nextPosition = new int[2];
        switch (direction) {
            case 'U':
                //上一步向上

                // 下一步向左
                nextPosition[0] = position[0];
                nextPosition[1] = position[1] - 1;
                minSteps = moveNext(maze, nextPosition, 'L', steps + 1, minSteps);

                // 下一步向右
                nextPosition[0] = position[0];
                nextPosition[1] = position[1] + 1;
                minSteps = moveNext(maze, nextPosition, 'R', steps + 1, minSteps);

                // 下一步向上
                nextPosition[0] = position[0] - 1;
                nextPosition[1] = position[1];
                minSteps = moveNext(maze, nextPosition, 'U', steps + 1, minSteps);
                break;
            case 'D':
                // 上一步向下

                // 下一步向左
                nextPosition[0] = position[0];
                nextPosition[1] = position[1] - 1;
                minSteps = moveNext(maze, nextPosition, 'L', steps + 1, minSteps);

                // 下一步向右
                nextPosition[0] = position[0];
                nextPosition[1] = position[1] + 1;
                minSteps = moveNext(maze, nextPosition, 'R', steps + 1, minSteps);

                // 下一步向下
                nextPosition[0] = position[0] + 1;
                nextPosition[1] = position[1];
                minSteps = moveNext(maze, nextPosition, 'D', steps + 1, minSteps);
                break;
            case 'L':
                // 上一步向左

                // 下一步向左
                nextPosition[0] = position[0];
                nextPosition[1] = position[1] - 1;
                minSteps = moveNext(maze, nextPosition, 'L', steps + 1, minSteps);

                // 下一步向上
                nextPosition[0] = position[0] - 1;
                nextPosition[1] = position[1];
                minSteps = moveNext(maze, nextPosition, 'U', steps + 1, minSteps);

                // 下一步向下
                nextPosition[0] = position[0] + 1;
                nextPosition[1] = position[1];
                minSteps = moveNext(maze, nextPosition, 'D', steps + 1, minSteps);
                break;
            case 'R':
                // 上一步向右

                // 下一步向右
                nextPosition[0] = position[0];
                nextPosition[1] = position[1] + 1;
                minSteps = moveNext(maze, nextPosition, 'R', steps + 1, minSteps);

                // 下一步向上
                nextPosition[0] = position[0] - 1;
                nextPosition[1] = position[1];
                minSteps = moveNext(maze, nextPosition, 'U', steps + 1, minSteps);

                // 下一步向下
                nextPosition[0] = position[0] + 1;
                nextPosition[1] = position[1];
                minSteps = moveNext(maze, nextPosition, 'D', steps + 1, minSteps);
                break;
            default:
                throw new IllegalArgumentException("未知的方向");
        }
        return minSteps;
    }

    public int moveNext(char[][] maze, int[] nexPosition, char direction, int steps, int minSteps) {
        if (maze[nexPosition[0]][nexPosition[1]] == '.') {
            int tmpSteps = move(maze, nexPosition, direction, steps, minSteps);
            if (tmpSteps != -1 && tmpSteps < minSteps) {
                minSteps = tmpSteps;
            }
        }
        return minSteps;
    }

    public static void main(String[] args) {
        Set<Integer[]> a = new HashSet<>();
        a.add(new Integer[]{1, 2});
        a.add(new Integer[]{1, 2});
        for (Integer[] b : a) {
            System.out.println(b[0] + " " + b[1]);
        }
    }
}
