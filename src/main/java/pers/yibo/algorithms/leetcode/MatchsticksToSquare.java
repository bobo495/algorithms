package pers.yibo.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 473. 火柴拼正方形
 * <p>
 * https://leetcode-cn.com/problems/matchsticks-to-square
 * <p>
 * 还记得童话《卖火柴的小女孩》吗？现在，你知道小女孩有多少根火柴，请找出一种能使用所有火柴拼成一个正方形的方法。不能折断火柴，可以把火柴连接起来，并且每根火柴都要用到。
 * <p>
 * 输入为小女孩拥有火柴的数目，每根火柴用其长度表示。输出即为是否能用所有的火柴拼成正方形。
 *
 * @author yibo
 * @date 2021-10-25 15:40
 **/
public class MatchsticksToSquare {

    public boolean makesquare(int[] matchsticks) {
        int sum = 0;
        for (int matchstick : matchsticks) {
            sum += matchstick;
        }

        if (sum % 4 != 0) {
            return false;
        }

        int sideLength = sum / 4;

        Arrays.sort(matchsticks);
        return addOne(matchsticks, matchsticks.length - 1, new int[]{0, 0, 0, 0}, sideLength);
    }

    public boolean addOne(int[] matchsticks, int index, int[] sides, int sideLength) {
        if (index == -1) {
            // 所有边都放完了则返回true
            return true;
        }
        Set<Integer> set = new HashSet<>();

        for (int i = 0; i < sides.length; i++) {
            int[] copy = sides.clone();
            if (copy[i] + matchsticks[index] <= sideLength && !set.contains(sides[i])) {
                // 哪条边能放下就尝试
                copy[i] += matchsticks[index];
                if (addOne(matchsticks, index - 1, copy, sideLength)) {
                    return true;
                }
                set.add(sides[i]);
            }
        }

        // 放不下去了返回false
        return false;
    }
}
