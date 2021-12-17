package pers.yibo.algorithms.leetcode;

/**
 * 1518. 换酒问题
 * <p>
 * https://leetcode-cn.com/problems/water-bottles
 * <p>
 * 小区便利店正在促销，用 numExchange 个空酒瓶可以兑换一瓶新酒。你购入了 numBottles 瓶酒。
 * <p>
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * <p>
 * 请你计算 最多 能喝到多少瓶酒。
 *
 * @author yibo
 * @date 2021-12-17 09:13
 **/
public class WaterBottles {
    public int numWaterBottles(int numBottles, int numExchange) {
        int sum = numBottles;
        while (numBottles >= numExchange) {
            int exchange = numBottles / numExchange;
            sum += exchange;
            numBottles = numBottles + exchange - exchange * numExchange;
        }
        return sum;
    }
}
