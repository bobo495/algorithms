package pers.yibo.algorithms.leetcode;

/**
 * 287. 寻找重复数
 * <p>
 * https://leetcode-cn.com/problems/find-the-duplicate-number
 * <p>
 * 给定一个包含n + 1 个整数的数组nums ，其数字都在 1 到 n之间（包括 1 和 n），可知至少存在一个重复的整数。
 * <p>
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 * <p>
 * 你设计的解决方案必须不修改数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * @author yibo
 * @date 2021-11-08 14:21
 **/
public class FindTheDuplicateNumber {
    /**
     * 快慢指针方法，找到环形链表多出的一段
     */
    public int findDuplicate(int[] nums) {
        int slow = nums[0];
        int fast = nums[slow];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        slow = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }

    public static void main(String[] args) {
        FindTheDuplicateNumber f = new FindTheDuplicateNumber();
        System.out.println(f.findDuplicate(new int[]{1, 2, 3, 4, 5, 2}));
    }
}
