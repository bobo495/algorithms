package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 475. 供暖器
 * <p>
 * https://leetcode-cn.com/problems/heaters
 * <p>
 * 冬季已经来临。你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * <p>
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * <p>
 * 现在，给出位于一条水平线上的房屋houses 和供暖器heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * <p>
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 *
 * @author yibo
 * @date 2021-12-20 10:38
 **/
public class Heaters {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);

        int max = 0;
        int heaterIndex = 0;

        for (int house : houses) {
            if (heaterIndex == heaters.length - 1) {
                max = Math.max(max, Math.abs(heaters[heaterIndex] - house));
            } else {
                if (house < heaterIndex) {
                    max = Math.max(max, heaters[heaterIndex] - house);
                } else {
                    while (true) {
                        // 距离左边近
                        if (house - heaters[heaterIndex] < heaters[heaterIndex + 1] - house) {
                            max = Math.max(max, Math.abs(house - heaters[heaterIndex]));
                            break;
                        } else {
                            heaterIndex++;
                            if (heaterIndex == heaters.length - 1) {
                                max = Math.max(max, Math.abs(heaters[heaterIndex] - house));
                                break;
                            }
                        }
                    }
                }
            }
        }
        return max;
    }
}
