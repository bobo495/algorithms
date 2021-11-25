package pers.yibo.algorithms.leetcode;

/**
 * 458. 可怜的小猪
 * <p>
 * https://leetcode-cn.com/problems/poor-pigs
 * <p>
 * 有 buckets 桶液体，其中 正好 有一桶含有毒药，其余装的都是水。它们从外观看起来都一样。为了弄清楚哪只水桶含有毒药，你可以喂一些猪喝，通过观察猪是否会死进行判断。不幸的是，你只有 minutesToTest 分钟时间来确定哪桶液体是有毒的。
 * <p>
 * 输入：buckets = 1000, minutesToDie = 15, minutesToTest = 60
 * 输出：5
 *
 * @author yibo
 * @date 2021-11-25 11:18
 **/
public class PoorPigs {
    public int poorPigs(int buckets, int minutesToDie, int minutesToTest) {
        if (buckets == 1) {
            return 0;
        }
        // 测试轮数+1
        int rounds = minutesToTest / minutesToDie + 1;

        int ans = 1;
        // n只猪能测试的最大罐子数为：(轮数+1)^n
        int max = ans * rounds;

        while (max < buckets) {
            max *= rounds;
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        PoorPigs p = new PoorPigs();
        System.out.println(p.poorPigs(1, 1, 1));
    }
}
