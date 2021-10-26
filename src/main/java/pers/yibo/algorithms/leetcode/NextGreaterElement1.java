package pers.yibo.algorithms.leetcode;

/**
 * 496. 下一个更大元素 I
 * <p>
 * https://leetcode-cn.com/problems/next-greater-element-i
 * <p>
 * 给你两个 没有重复元素 的数组nums1 和nums2，其中nums1是nums2的子集。
 * <p>
 * 请你找出 nums1中每个元素在nums2中的下一个比其大的值。
 * <p>
 * nums1中数字x的下一个更大元素是指x在nums2中对应位置的右边的第一个比x大的元素。如果不存在，对应位置输出 -1 。
 * <p>
 *
 * @author yibo
 * @date 2021-10-26 11:14
 **/
public class NextGreaterElement1 {
    /**
     * 单调盏方法，获取nums2中每一个元素的下一个greater，并存储到hashMap中
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 单调栈
        int[] stack = new int[nums2.length];
        int stackIndex = -1;

        // hash表，数字最大值为10000，因此可用长度10000的数组替代hashMap
        int[] hash=new int[10000];
//        Map<Integer, Integer> hashMap = new HashMap<>(16);

        for (int i = nums2.length - 1; i >= 0; i--) {

            while (stackIndex >= 0 && stack[stackIndex] < nums2[i]) {
                // 执行pop，直至栈顶为greater
                stackIndex--;
            }
            if (stackIndex == -1) {
                // 栈为空，说明这个数字右侧没有greater数字
//                hashMap.put(nums2[i], -1);
                hash[nums2[i]]=-1;
            } else {
                // 栈顶为greater数字
//                hashMap.put(nums2[i], stack[stackIndex]);
                hash[nums2[i]]=stack[stackIndex];
            }
            // 当前数字入栈
            stackIndex += 1;
            stack[stackIndex] = nums2[i];
        }

        for (int i = 0; i < nums1.length; i++) {
//            nums1[i] = hashMap.get(nums1[i]);
            nums1[i]=hash[nums1[i]];
        }
        return nums1;
    }

    /**
     * 二重遍历
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] nextGreaterElement2(int[] nums1, int[] nums2) {
        for (int i = 0; i < nums1.length; i++) {
            int value = nums1[i];
            boolean findIndex = false;
            for (int k : nums2) {
                if (k == value) {
                    findIndex = true;
                }
                if (findIndex && k > value) {
                    nums1[i] = k;
                    break;
                }
            }
            if (nums1[i] == value) {
                nums1[i] = -1;
            }
        }
        return nums1;
    }
}
