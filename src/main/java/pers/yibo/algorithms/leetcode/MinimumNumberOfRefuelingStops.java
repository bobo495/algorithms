package pers.yibo.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 871. 最低加油次数
 * <p>
 * https://leetcode-cn.com/problems/minimum-number-of-refueling-stops
 * <p>
 * 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target英里处。
 * <p>
 * 沿途有加油站，每个station[i]代表一个加油站，它位于出发位置东面station[i][0]英里处，并且有station[i][1]升汽油。
 * <p>
 * 假设汽车油箱的容量是无限的，其中最初有startFuel升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * <p>
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * <p>
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * <p>
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 *
 * @author yibo
 * @date 2021-10-25 10:57
 **/
public class MinimumNumberOfRefuelingStops {

    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        return findNext(target, startFuel, stations, 0, new HashSet<>());
    }

    public int findNext(int target, int remainFuel, int[][] stations, int refuelingNumber, Set<Integer> history) {
        if (remainFuel >= target) {
            // 不需要再加油的情况
            return refuelingNumber;
        }

        int maxFuel = 0;
        int maxIndex = 0;
        for (int i = 0; i < stations.length; i++) {
            if (stations[i][0] > remainFuel) {
                break;
            }
            if (!history.contains(i) && stations[i][1] > maxFuel) {
                maxFuel = stations[i][1];
                maxIndex = i;
            }
        }

        if (maxFuel == 0) {
            // 没找到有效值
            return -1;
        }

        history.add(maxIndex);
        return findNext(target, remainFuel + maxFuel, stations, refuelingNumber + 1, history);
    }
}
