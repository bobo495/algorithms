package pers.yibo.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 55. 跳跃游戏
 * <p>
 * https://leetcode-cn.com/problems/jump-game/
 *
 * @author yibo
 * 2022/2/19 10:04
 */
public class JumpGame {
    /**
     * 每一步可达的最大值
     */
    public boolean canJump(int[] nums) {
        int max = nums[0];
        for (int i = 0; i <= max; i++) {
            if (nums[i] + i >= nums.length - 1) {
                return true;
            }
            if (nums[i] + i > max) {
                max = nums[i] + i;
            }
        }
        return false;
    }

    /**
     * 广度搜索
     */
    public boolean canJump2(int[] nums) {

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
