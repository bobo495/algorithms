package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 825. 适龄的朋友
 * <p>
 * https://leetcode-cn.com/problems/friends-of-appropriate-ages/
 * <p>
 * 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * <p>
 * 返回在该社交媒体网站上产生的好友请求总数。
 * <p>
 * 用户 x 不会向用户y发送好友请求的条件：
 * <p>
 * 1. {@code age[y] <= 0.5 * age[x] + 7}
 * <p>
 * 2. {@code age[x] < age[y]}
 * <p>
 * 3. {@code age[y] > 100 && age[x] < 100}
 *
 * @author yibo
 * @date 2021-12-27 10:05
 **/
public class FriendsOfAppropriateAges {

    public int numFriendRequests(int[] ages) {

        if (ages.length <= 1) {
            return 0;
        }

        Arrays.sort(ages);
        int leftIndex = 0;
        int ans = 0;
        int repeat = 0;
        for (int i = 1; i < ages.length; i++) {
            int minAge = ages[i] / 2 + 7;

            // 找到可以发送好友请求的最小索引
            while (ages[leftIndex] <= minAge && leftIndex < i - 1) {
                leftIndex++;
            }
            if (ages[leftIndex] > minAge) {
                ans += i - leftIndex;
            }
            // 处理重复元素
            if (ages[i] > 14 && ages[i] == ages[i - 1]) {
                repeat++;
            } else {
                if (repeat != 0) {
                    ans += repeat * (repeat + 1) / 2;
                }
                repeat = 0;
            }
        }
        if (repeat != 0) {
            ans += repeat * (repeat + 1) / 2;
        }
        return ans;
    }

    public static void main(String[] args) {
        FriendsOfAppropriateAges f = new FriendsOfAppropriateAges();
        int[] ages = new int[]{73, 106, 39, 6, 26, 15, 30, 100, 71, 35, 46, 112, 6, 60, 110};
        System.out.println(f.numFriendRequests(ages));
    }
}
