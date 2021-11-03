package pers.yibo.algorithms.leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1499. 满足不等式的最大值
 * <p>
 * https://leetcode-cn.com/problems/max-value-of-equation/
 *
 * @author yibo
 * @date 2021-11-02 16:51
 **/
public class MaxValueOfEquation {
    public int findMaxValueOfEquation2(int[][] points, int k) {
        int result = Integer.MIN_VALUE;
        // x , y-x
        Deque<int[]> deque = new LinkedList<>();
        deque.add(new int[]{points[0][0], points[0][1] - points[0][0]});

        for (int i = 1; i < points.length; i++) {
            while (!deque.isEmpty() && points[i][0] - deque.getFirst()[0] > k) {
                deque.pollFirst();
            }
            if (!deque.isEmpty()) {
                result = Math.max(result, points[i][0] + points[i][1] + deque.getFirst()[1]);
            }
            while (!deque.isEmpty() && deque.getLast()[1] < points[i][1] - points[i][0]) {
                deque.pollLast();
            }
            deque.add(new int[]{points[i][0], points[i][1] - points[i][0]});
        }
        return result;
    }


    public int findMaxValueOfEquation(int[][] points, int k) {
        int result = Integer.MIN_VALUE;

        // x , y-x
        int[][] queue = new int[points.length][2];
        queue[0][0] = points[0][0];
        queue[0][1] = points[0][1] - points[0][0];
        int head = 0;
        int tail = 0;

        for (int i = 1; i < points.length; i++) {
            for (int j = head; j <= tail; j++) {
                System.out.print(" " + Arrays.toString(queue[j]) + " ");
            }
            System.out.println(head + " " + tail + " " + i);

            // 从头清除超出k的结果
            while (head <= tail && points[i][0] - queue[head][0] > k) {
                head++;
            }

            // 队列不为空
            if (head <= tail) {
                result = Math.max(result, points[i][0] + points[i][1] + queue[head][1]);
            }

            int dist = points[i][1] - points[i][0];
            // 从尾清除比当前y-x更小的结果
            while (tail >= head && queue[tail][1] < dist) {
                tail--;
            }

            queue[++tail][0] = points[i][0];
            queue[tail][1] = dist;
        }

        return result;
    }
}
