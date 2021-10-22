package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 229. 求众数 II
 * <p>
 * https://leetcode-cn.com/problems/majority-element-ii/
 * <p>
 * 给定一个大小为 n 的整数数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
 * <p>
 * 输入：[1,1,1,3,3,2,2,2]
 * 输出：[1,2]
 *
 * @author yibo
 * @date 2021-10-22 11:09
 **/
public class MajorityElement2 {

    /**
     * 摩尔投票法
     * <p>
     * 核心：对拼消耗，剩余的则为最多的
     * <p>
     * 论文参考: https://www.cs.ou.edu/~rlpage/dmtools/mjrty.pdf
     * <p>
     * wiki: https://zh.wikipedia.org/wiki/多数投票算法
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        // 最大的两个数
        int num1 = 0, num2 = 0;
        // 最大的数对应的剩余数量
        int vote1 = 0, vote2 = 0;
        for (int num : nums) {
            if (num1 == num) {
                vote1++;
            } else if (num2 == num) {
                vote2++;
            } else if (vote1 == 0) {
                // num1被消耗完
                num1 = num;
                vote1++;
            } else if (vote2 == 0) {
                // num2被消耗完
                num2 = num;
                vote2++;
            } else {
                // 当前两个数均未被消耗完的情况下来了第三个数，则num1 和 num2一起消耗一个
                vote1--;
                vote2--;
            }
        }

        // 校验num1和num2是否超过1/3
        // 重置vote
        vote1 = 0;
        vote2 = 0;
        // 最多有两个
        List<Integer> result = new ArrayList<>(2);
        int majorLength = nums.length / 3;

        for (int num : nums) {
            if(num1==num){
                vote1++;
            }else if(num2==num){
                vote2++;
            }
        }
        if(vote1>majorLength){
            result.add(num1);
        }
        if(vote2>majorLength){
            result.add(num2);
        }
        return result;
    }

    /**
     * 普通方法
     *
     * @param nums
     * @return
     */
    public List<Integer> majorityElement2(int[] nums) {
        // 最多有两个
        List<Integer> result = new ArrayList<>(2);

        int majorLength = nums.length / 3;

        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > majorLength) {
                result.add(entry.getKey());
                if (result.size() == 2) {
                    return result;
                }
            }
        }

        return result;
    }
}
