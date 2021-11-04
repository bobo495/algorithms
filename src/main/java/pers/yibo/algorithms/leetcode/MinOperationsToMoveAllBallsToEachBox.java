package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 1769. 移动所有球到每个盒子所需的最小操作数
 * <p>
 * https://leetcode-cn.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box
 * <p>
 * 每个 answer[i] 都需要根据盒子的 初始状态 进行计算。
 * <p>
 * 输入：boxes = "110"
 * 输出：[1,1,3]
 * 解释：每个盒子对应的最小操作数如下：
 * 1) 第 1 个盒子：将一个小球从第 2 个盒子移动到第 1 个盒子，需要 1 步操作。
 * 2) 第 2 个盒子：将一个小球从第 1 个盒子移动到第 2 个盒子，需要 1 步操作。
 * 3) 第 3 个盒子：将一个小球从第 1 个盒子移动到第 3 个盒子，需要 2 步操作。将一个小球从第 2 个盒子移动到第 3 个盒子，需要 1 步操作。共计 3 步操作。
 *
 * @author yibo
 * @date 2021-11-04 15:58
 **/
public class MinOperationsToMoveAllBallsToEachBox {
    /**
     * 动态规划
     */
    public int[] minOperations(String boxes) {
        char[] chars = boxes.toCharArray();
        int length = boxes.length();
        int[] ans = new int[length];
        int left = 0;
        int right = 0;

        if (chars[0] == '1') {
            left++;
        }

        for (int i = 1; i < length; i++) {
            if (chars[i] == '1') {
                right++;
                ans[0] += i;
            }
        }

        for (int i = 1; i < boxes.length(); i++) {
            ans[i] = ans[i - 1] - right + left;
            if (chars[i] == '1') {
                left++;
                right--;
            }
        }

        return ans;
    }


    public int[] minOperations2(String boxes) {
        int[] ans = new int[boxes.length()];

        for (int i = 0; i < boxes.length(); i++) {
            char c = boxes.charAt(i);
            if (c == '0') {
                continue;
            }
            for (int j = 0; j < ans.length; j++) {
                ans[j] += Math.abs(i - j);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        MinOperationsToMoveAllBallsToEachBox m = new MinOperationsToMoveAllBallsToEachBox();
        System.out.println(Arrays.toString(m.minOperations("001011")));
    }
}
