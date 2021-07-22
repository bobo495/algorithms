package pers.yibo.algorithms.fundamentals;

/**
 * 二分查找
 *
 * @author yibo
 * @date 2021-07-22 14:45
 **/
public class BinarySearch {
    /**
     * 二分查找方法实现 有序数组查找指定key
     *
     * @param key  待查找的key值
     * @param nums 有序数组
     * @return 不存在时返回 -1，存在时返回key值在数组中的下标
     */
    public static int rank(int key, int[] nums) {
        // 左侧索引
        int lowIndex = 0;
        // 右侧索引
        int highIndex = nums.length - 1;

        // lowIndex > highIndex 则终止循环 => 返回 -1
        while (lowIndex <= highIndex) {
            // 中间索引
            int midIndex = lowIndex + (highIndex - lowIndex) / 2;
            if (key < nums[midIndex]) {
                // key 小于 中间索引对应值，则 highIndex 移动至 midIndex-1
                highIndex = midIndex - 1;
            } else if (key > nums[midIndex]) {
                // key 大于 中间索引对应值，则 lowIndex 移动至 midIndex+1
                lowIndex = midIndex + 1;
            } else {
                // 相等说明找到key值，返回索引下标
                return midIndex;
            }
        }

        // 未找到则返回 -1
        return -1;
    }
}
