package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 506. 相对名次
 * <p>
 * https://leetcode-cn.com/problems/relative-ranks/
 * <p>
 * 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 *
 * @author yibo
 * @date 2021-12-02 09:14
 **/
public class RelativeRanks {
    public String[] findRelativeRanks(int[] score) {

        int[] index = sortIndex(score);

        String[] rank = new String[score.length];
        // 初始化index
        if (rank.length >= 1) {
            rank[index[0]] = "Gold Medal";
        }
        if (rank.length >= 2) {
            rank[index[1]] = "Silver Medal";
        }
        if (rank.length >= 3) {
            rank[index[2]] = "Bronze Medal";
        }
        for (int i = 3; i < score.length; i++) {
            rank[index[i]] = String.valueOf(i + 1);
        }

        return rank;
    }

    // shell 排序：5ms
    public int[] sortIndex(int[] nums) {
        int[] index = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }

        int step = 5;
        int h = 1;
        while (h < nums.length / step) {
            h = h * step + 1;
        }

        while (h >= 1) {
            for (int i = h; i < nums.length; i++) {
                for (int j = i; j >= h && nums[j] > nums[j - h]; j -= h) {
                    exchange(index, j, j - h);
                    exchange(nums, j, j - h);
                }
            }
            h = h / step;
        }
        return index;
    }

    public void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }

    public static void main(String[] args) {
        RelativeRanks r = new RelativeRanks();
        int[] scores = new int[]{10, 3, 8, 9, 4};
        System.out.println(Arrays.toString(r.findRelativeRanks(scores)));
    }
}
