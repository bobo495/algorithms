package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 870. 优势洗牌
 * <p>
 * https://leetcode-cn.com/problems/advantage-shuffle
 * <p>
 * 给定两个大小相等的数组A和B，A 相对于 B 的优势可以用满足A[i] > B[i]的索引 i的数目来描述。
 * <p>
 * 返回A的任意排列，使其相对于 B的优势最大化。
 * <p>
 * 输入：A = [2,7,11,15], B = [1,10,4,11]
 * 输出：[2,11,7,15]
 * <p>
 * 输入：A = [12,24,8,32], B = [13,25,32,11]
 * 输出：[24,32,8,12]
 *
 * @author yibo
 * @date 2021-11-02 14:46
 **/
public class AdvantageShuffle {

    /**
     * nums1 排序，nums2排序带索引
     * <p>
     * 从最大值开始比较，若nums1更大，则放入nums1最大值，否则放入nums1最小值
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        // 对nums2排序，记录排序后的索引
        Integer[] nums2Index = new Integer[nums2.length];
        for (int i = 0; i < nums2Index.length; i++) {
            nums2Index[i] = i;
        }
        Arrays.sort(nums2Index, Comparator.comparingInt(o -> nums2[o]));

        int[] result = new int[nums1.length];

        int highIndex1 = nums2.length - 1;
        int lowIndex1 = 0;
        for (int i = nums2.length - 1; i >= 0; i--) {
            if (nums1[highIndex1] > nums2[nums2Index[i]]) {
                result[nums2Index[i]] = nums1[highIndex1];
                highIndex1--;
            } else {
                result[nums2Index[i]] = nums1[lowIndex1];
                lowIndex1++;
            }
        }

        return result;
    }


    /**
     * nums1排序+二分查找，每次查找移除value
     */
    public int[] advantageCount2(int[] nums1, int[] nums2) {
        int[] sortedNums1 = nums1.clone();
        Arrays.sort(sortedNums1);

        List<Integer> sortedList1 = new ArrayList<>();
        for (int num : sortedNums1) {
            sortedList1.add(num);
        }

        for (int i = 0; i < nums2.length; i++) {
            int value2 = nums2[i];

            int l = 0;
            int r = sortedList1.size() - 1;
            int index = l;

            boolean find = false;
            while (l <= r) {
                index = (l + r) / 2;
                int value1 = sortedList1.get(index);
                if (value1 <= value2) {
                    l = index + 1;
                } else {
                    if (index == 0) {
                        find = true;
                        break;
                    }
                    if (sortedList1.get(index - 1) <= value2) {
                        find = true;
                        break;
                    }
                    r = index - 1;
                }
            }

            if (find) {
                nums1[i] = sortedList1.get(index);
                sortedList1.remove(index);
            } else {
                nums1[i] = sortedList1.get(0);
                sortedList1.remove(0);
            }
        }
        return nums1;
    }
}
