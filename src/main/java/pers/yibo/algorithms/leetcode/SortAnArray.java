package pers.yibo.algorithms.leetcode;

import java.util.Arrays;
import java.util.Random;

/**
 * 912. 排序数组
 *
 * @author yibo
 * @date 2021-11-11 16:01
 **/
public class SortAnArray {
    /**
     * java自带排序
     * <p>
     * 6 ms	47.2 MB
     */
    public int[] sortArray(int[] nums) {
        Arrays.sort(nums);
        return nums;
    }


    /**
     * 桶排序，建最多的桶，每个num通过hash映射到桶内，空间换时间
     * <p>
     * 执行用时：
     * 3 ms
     * , 在所有 Java 提交中击败了
     * 99.28%
     * 的用户
     * 内存消耗：
     * 48.3 MB
     * , 在所有 Java 提交中击败了
     * 45.32%
     * 的用户
     * 通过测试用例：
     * 13 / 13
     */
    public int[] sortArray5(int[] nums) {
        int[] buckets = new int[100001];
        for (int num : nums) {
            buckets[num + 50000]++;
        }
        int index = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i] > 0) {
                nums[index++] = i - 50000;
                buckets[i]--;
            }
            if (index == nums.length) {
                return nums;
            }
        }
        return nums;
    }


    /**
     * 希尔排序
     * <p>
     * 执行用时：
     * 10 ms
     * , 在所有 Java 提交中击败了
     * 86.40%
     * 的用户
     * 内存消耗：
     * 47.4 MB
     * , 在所有 Java 提交中击败了
     * 57.82%
     * 的用户
     * 通过测试用例：
     * 13 / 13
     */
    public int[] sortArray4(int[] nums) {
        int step = 5;
        int h = 1;
        while (h < nums.length / step) {
            h = h * step + 1;
        }

        while (h >= 1) {
            for (int i = h; i < nums.length; i++) {
                for (int j = i; j >= h && nums[j] < nums[j - h]; j -= h) {
                    exchange(nums, j, j - h);
                }
            }
            h = h / step;
        }
        return nums;
    }

    public void exchange(int[] a, int i, int j) {
        int tmp = a[i];
        a[i] = a[j];
        a[j] = tmp;
    }


    /**
     * 三向快排
     * 执行用时：
     * 20 ms
     * , 在所有 Java 提交中击败了
     * 45.72%
     * 的用户
     * 内存消耗：
     * 46.7 MB
     * , 在所有 Java 提交中击败了
     * 97.09%
     * 的用户
     * 通过测试用例：
     * 13 / 13
     */
    public int[] sortArray3(int[] nums) {
        if (nums.length <= 1) {
            return nums;
        }
        shuffle(nums);
        sort(nums, 0, nums.length - 1);
        return nums;
    }

    public void sort(int[] nums, int low, int high) {
        if (high <= low) {
            return;
        }
        int lt = low, index = low + 1, gt = high;
        int splitElement = nums[low];
        while (index <= gt) {
            int compare = Integer.compare(nums[index], splitElement);
            if (compare < 0) {
                exchange(nums, index++, lt++);
            } else if (compare > 0) {
                exchange(nums, index, gt--);
            } else {
                index++;
            }
        }
        sort(nums, low, lt - 1);
        sort(nums, gt + 1, high);
    }

    public void shuffle(int[] a) {
        Random random = new Random();
        for (int i = 0; i < a.length; i++) {
            exchange(a, i, random.nextInt(a.length - 1));
        }
    }


    /**
     * 堆排
     * <p>
     * 执行用时：
     * 16 ms
     * , 在所有 Java 提交中击败了
     * 54.37%
     * 的用户
     * 内存消耗：
     * 46.6 MB
     * , 在所有 Java 提交中击败了
     * 98.08%
     * 的用户
     * 通过测试用例：
     * 13 / 13
     */
    public int[] sortArray2(int[] nums) {
        int size = nums.length;
        for (int i = size / 2; i >= 0; i--) {
            sink(nums, i + 1, size);
        }
        while (size > 1) {
            exchange(nums, 0, --size);
            sink(nums, 1, size);
        }
        return nums;
    }

    public void sink(int[] nums, int index, int size) {
        while (index * 2 - 1 < size) {
            int childIndex = index * 2;
            if (childIndex < size && nums[childIndex - 1] < nums[childIndex]) {
                childIndex++;
            }
            if (nums[childIndex - 1] < nums[index - 1]) {
                break;
            }
            exchange(nums, index - 1, childIndex - 1);
            index = childIndex;
        }
    }

    public static void main(String[] args) {
        SortAnArray s = new SortAnArray();
        int[] nums = new int[]{5, 3, 1, 2};
        System.out.println(Arrays.toString(s.sortArray(nums)));
    }
}
