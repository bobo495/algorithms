package pers.yibo.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 55. 跳跃游戏
 *
 * @author yibo
 * 2022/2/19 10:04
 */
public class JumpGame {
    /**
     * 广度搜索
     */
    public boolean canJump(int[] nums) {

        boolean[] marked = new boolean[nums.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        marked[0] = true;
        // 每个索引最多入队一次
        while (!queue.isEmpty()) {
            int index = queue.poll();
            if (index + nums[index] >= nums.length - 1) {
                return true;
            }
            // 反向入队
            for (int i = index + nums[index]; i >= Math.max(1, index - nums[index]); i--) {
                if (!marked[i]) {
                    queue.add(i);
                    marked[i] = true;
                }
            }
        }
        return false;
    }
}
