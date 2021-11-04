package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 407. 接雨水 II
 * <p>
 * https://leetcode-cn.com/problems/trapping-rain-water-ii/
 * <p>
 * 给你一个 m x n 的矩阵，其中的值均为非负整数，代表二维高度图每个单元的高度，请计算图中形状最多能接多少体积的雨水。
 * <p>
 * 输入: heightMap =
 * [[1,4,3,1,3,2],
 * [3,2,1,3,2,4],
 * [2,3,3,2,3,1]]
 * 输出: 4
 * 解释: 下雨后，雨水将会被上图蓝色的方块中。总的接雨水量为1+2+1=4。
 *
 * @author yibo
 * @date 2021-11-03 10:15
 **/
public class TrappingRainWater2 {

    class Node {
        int[] point;
        Node next;
    }

    public int trapRainWater(int[][] heightMap) {
        int row = heightMap.length;
        int col = heightMap[0].length;
        if (row < 3 || col < 3) {
            // 至少3X3的矩阵才能接住雨水
            return 0;
        }
        int[][] heightWater = new int[row][col];

        // 获取heightMap最大值
        int maxHeight = 0;
        for (int[] ints : heightMap) {
            for (int j = 0; j < col; j++) {
                maxHeight = Math.max(maxHeight, ints[j]);
            }
        }

        // 初始化heightWater
        for (int i = 0; i < row; i++) {
            Arrays.fill(heightWater[i], maxHeight);
        }

        // 链表，从外圈开始,获取每个坐标的最小高度
        Node node = new Node();
        // 末尾坐标
        Node tail = node;
        // 初始坐标,用于更新heightWater
        Node head = node;

        // 初始化外圈
        for (int j = 0; j < col; j++) {
            if (heightWater[0][j] > heightMap[0][j]) {
                heightWater[0][j] = heightMap[0][j];
                tail.point = new int[]{0, j};
                tail.next = new Node();
                tail = tail.next;
            }
            if (heightWater[row - 1][j] > heightMap[row - 1][j]) {
                heightWater[row - 1][j] = heightMap[row - 1][j];
                tail.point = new int[]{row - 1, j};
                tail.next = new Node();
                tail = tail.next;
            }
        }
        for (int i = 1; i < row - 1; i++) {
            if (heightWater[i][0] > heightMap[i][0]) {
                heightWater[i][0] = heightMap[i][0];
                tail.point = new int[]{i, 0};
                tail.next = new Node();
                tail = tail.next;
            }
            if (heightWater[i][col - 1] > heightMap[i][col - 1]) {
                heightWater[i][col - 1] = heightMap[i][col - 1];
                tail.point = new int[]{i, col - 1};
                tail.next = new Node();
                tail = tail.next;
            }
        }

        // 上下左右
        int[] dir = {-1, 0, 1, 0, -1};
        while (head.point != null) {
            int x = head.point[0];
            int y = head.point[1];
            for (int i = 0; i < 4; i++) {
                int x1 = x + dir[i];
                int y1 = y + dir[i + 1];
                if (x1 < 0 || x1 >= row || y1 < 0 || y1 >= col) {
                    // 越界
                    continue;
                }
                if (heightWater[x1][y1] > heightWater[x][y] && heightWater[x1][y1] > heightMap[x1][y1]) {
                    // 校验x1 y1是否比x y和heightMap x1,y1 大，如果是，则取两者的最大值填充，并且将x1 y1添加到队列末端
                    heightWater[x1][y1] = Math.max(heightWater[x][y], heightMap[x1][y1]);
                    tail.point = new int[]{x1, y1};
                    tail.next = new Node();
                    tail = tail.next;
                }
            }
            head = head.next;
        }

        // 统计结果
        int result = 0;
        for (int i = 1; i < row - 1; i++) {
            for (int j = 1; j < col - 1; j++) {
                result += heightWater[i][j] - heightMap[i][j];
            }
        }

        for (int[] arr : heightWater) {
            System.out.println(Arrays.toString(arr));
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] heightMap = new int[][]{
                {19383, 10886, 12777, 16915, 17793, 18335, 15386, 10492, 16649, 11421},
                {12362, 27, 8690, 59, 7763, 3926, 540, 3426, 9172, 5736},
                {15211, 5368, 2567, 6429, 5782, 1530, 2862, 5123, 4067, 3135},
                {13929, 9802, 4022, 3058, 3069, 8167, 1393, 8456, 5011, 8042},
                {16229, 7373, 4421, 4919, 3784, 8537, 5198, 4324, 8315, 4370},
                {16413, 3526, 6091, 8980, 9956, 1873, 6862, 9170, 6996, 7281},
                {12305, 925, 7084, 6327, 336, 6505, 846, 1729, 1313, 5857},
                {16124, 3895, 9582, 545, 8814, 3367, 5434, 364, 4043, 3750},
                {11087, 6808, 7276, 7178, 5788, 3584, 5403, 2651, 2754, 2399},
                {19932, 5060, 9676, 3368, 7739, 12, 6226, 8586, 8094, 7539}
        };

        TrappingRainWater2 t = new TrappingRainWater2();
        System.out.println(t.trapRainWater(heightMap));
    }

}
