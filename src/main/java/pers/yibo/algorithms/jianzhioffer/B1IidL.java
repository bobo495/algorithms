package pers.yibo.algorithms.jianzhioffer;

/**
 * 剑指 Offer II 069. 山峰数组的顶部
 * <p>
 * https://leetcode-cn.com/problems/B1IidL
 * <p>
 * 符合下列属性的数组 arr 称为 山峰数组（山脉数组） ：
 * <p>
 * arr.length >= 3
 * 存在 i（0 < i< arr.length - 1）使得：
 * arr[0] < arr[1] < ... arr[i-1] < arr[i]
 * arr[i] > arr[i+1] > ... > arr[arr.length - 1]
 * 给定由整数组成的山峰数组 arr ，返回任何满足 arr[0] < arr[1] < ... arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1] 的下标 i ，即山峰顶部。
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [0,1,0]
 * 输出：1
 * <p>
 * 示例 2：
 * <p>
 * 输入：arr = [1,3,5,4,2]
 * 输出：2
 * <p>
 * 示例 3：
 * <p>
 * 输入：arr = [0,10,5,2]
 * 输出：1
 * <p>
 * 示例 4：
 * <p>
 * 输入：arr = [3,4,5,1]
 * 输出：2
 * <p>
 * 示例 5：
 * <p>
 * 输入：arr = [24,69,100,99,79,78,67,36,26,19]
 * 输出：2
 *
 * @author yibo
 * @date 2021-10-14 09:55
 **/
public class B1IidL {
    public int peakIndexInMountainArray(int[] arr) {
        int l = 0;
        int r = arr.length - 1;
        int index = arr.length / 2;
        while (true) {
            int indexValue = arr[index];
            // 左右均有
            int lValue = arr[index - 1];
            int rValue = arr[index + 1];
            if (indexValue > lValue && indexValue > rValue) {
                return index;
            } else if (lValue < indexValue && indexValue < rValue) {
                l = index;
                index = (index + r) / 2;
            } else {
                r = index;
                index = (index + l) / 2;
            }
        }
    }
}
