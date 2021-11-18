package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 350. 两个数组的交集 II
 * <p>
 * https://leetcode-cn.com/problems/intersection-of-two-arrays-ii/
 * <p>
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author yibo
 * @date 2021-11-18 09:37
 **/
public class IntersectionOfTwoArrays2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] tmp = new int[Math.max(nums1.length, nums2.length)];

        int index1 = 0;
        int index2 = 0;
        int length = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            int compare = Integer.compare(nums1[index1], nums2[index2]);
            if (compare < 0) {
                index1++;
            } else if (compare > 0) {
                index2++;
            } else {
                tmp[length++] = nums1[index1++];
                index2++;
            }
        }
        return Arrays.copyOf(tmp,length);
    }
}
