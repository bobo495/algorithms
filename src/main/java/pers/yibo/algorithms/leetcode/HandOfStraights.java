package pers.yibo.algorithms.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 846. 一手顺子
 * <p>
 * https://leetcode-cn.com/problems/hand-of-straights
 * <p>
 * Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false
 *
 * @author yibo
 * @date 2021-12-30 09:20
 **/
public class HandOfStraights {
    public boolean isNStraightHand(int[] hand, int groupSize) {

        // 相同牌的数量
        Map<Integer, Integer> mapHand = new HashMap<>(hand.length);
        for (int h : hand) {
            mapHand.put(h, mapHand.getOrDefault(h, 0) + 1);
        }

        Arrays.sort(hand);

        // 消除法
        for (int h : hand) {
            if (!mapHand.containsKey(h)) {
                continue;
            }
            for (int i = 0; i < groupSize; i++) {
                int num = i + h;
                if (!mapHand.containsKey(num)) {
                    return false;
                }
                int count = mapHand.get(num);
                if (count == 1) {
                    mapHand.remove(num);
                } else {
                    mapHand.put(num, count - 1);
                }
            }
        }

        // 所有的都可以被消除则符合条件
        return true;
    }
}
