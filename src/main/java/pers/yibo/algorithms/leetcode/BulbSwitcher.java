package pers.yibo.algorithms.leetcode;

/**
 * 319. 灯泡开关
 * <p>
 * https://leetcode-cn.com/problems/bulb-switcher
 * <p>
 * 初始时有n 个灯泡处于关闭状态。第一轮，你将会打开所有灯泡。接下来的第二轮，你将会每两个灯泡关闭一个。
 * <p>
 * 第三轮，你每三个灯泡就切换一个灯泡的开关（即，打开变关闭，关闭变打开）。第 i 轮，你每 i 个灯泡就切换一个灯泡的开关。直到第 n 轮，你只需要切换最后一个灯泡的开关。
 * <p>
 * 找出并返回 n轮后有多少个亮着的灯泡。
 *
 * @author yibo
 * @date 2021-11-15 15:40
 **/
public class BulbSwitcher {
    /**
     * i个灯泡比i-1个灯泡多一轮，并且第i轮不会影响前i-1个灯泡
     * <p>
     * 关键：得到第i个灯泡的最终结果
     * <p>
     * 对i进行质因数分解，可得到i切换开关的次数。若i需要亮着，若i有某个因数a，则一定有对应的因数b使得 a*b=i，因此仅有a=b时，满足条件
     */
    public int bulbSwitch(int n) {
        int light = 0;
        for (int i = 1; i * i <= n; i++) {
            light++;
        }
        return light;
    }

    /**
     * 暴力解法
     */
    public int bulbSwitch2(int n) {
        boolean[] status = new boolean[n];
        switchStatus(1, status);
        int light = 0;
        for (boolean s : status) {
            if (s) {
                light++;
            }
        }
        return light;
    }

    public void switchStatus(int interval, boolean[] status) {
        if (interval > status.length) {
            return;
        }

        for (int i = interval - 1; i < status.length; i += interval) {
            status[i] = !status[i];
        }
        switchStatus(interval + 1, status);
    }

    public static void main(String[] args) {
        BulbSwitcher b = new BulbSwitcher();
        System.out.println(b.bulbSwitch(3));
    }
}
