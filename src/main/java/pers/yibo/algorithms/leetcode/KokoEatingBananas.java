package pers.yibo.algorithms.leetcode;

/**
 * 875. 爱吃香蕉的珂珂
 * <p>
 * https://leetcode-cn.com/problems/koko-eating-bananas
 * <p>
 * 珂珂喜欢吃香蕉。这里有 N 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 H 小时后回来。
 * <p>
 * 珂珂可以决定她吃香蕉的速度 K （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 K 根。如果这堆香蕉少于 K 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * <p>
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * <p>
 * 返回她可以在 H 小时内吃掉所有香蕉的最小速度 K（K 为整数）。
 *
 * @author yibo
 * @date 2021-10-27 17:28
 **/
public class KokoEatingBananas {
    public int minEatingSpeed(int[] piles, int h) {
        int minSpeed = 1;
        int maxSpeed = 1000000000;

        int speed = maxSpeed;

        while (minSpeed < maxSpeed) {
            speed = (minSpeed + maxSpeed) / 2;
            if (eatPile(piles, h, speed)) {
                maxSpeed = speed;
            } else {
                minSpeed = speed + 1;
            }
        }
        return speed;
    }

    /**
     * 能否吃完
     */
    public boolean eatPile(int[] piles, int h, int speed) {
        int used = 0;
        for (int pile : piles) {
            used += (pile - 1) / speed + 1;
        }
        return used <= h;
    }
}
