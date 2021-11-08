package pers.yibo.algorithms.leetcode;

import java.util.Arrays;

/**
 * 299. 猜数字游戏
 * <p>
 * https://leetcode-cn.com/problems/bulls-and-cows
 * <p>
 * 给你一个秘密数字secret 和朋友猜测的数字guess ，请你返回对朋友这次猜测的提示。
 * <p>
 * 提示的格式为 "xAyB" ，x 是公牛个数， y 是奶牛个数，A 表示公牛，B表示奶牛。
 * <p>
 * 请注意秘密数字和朋友猜测的数字都可能含有重复数字。
 * <p>
 * 输入: secret = "1807", guess = "7810"
 * 输出: "1A3B"
 * 解释: 数字和位置都对（公牛）用 '|' 连接，数字猜对位置不对（奶牛）的采用斜体加粗标识。
 * "1807"
 * |
 * "7810"
 *
 * @author yibo
 * @date 2021-11-08 15:19
 **/
public class BullsAndCows {
    public String getHint(String secret, String guess) {
        int x = 0, y = 0;
        // 记录0-9在secret中的出现次数
        int[] countN = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                x++;
                continue;
            }
            int index = secret.charAt(i) - '0';
            if (countN[index] < 0) {
                y++;
            }
            countN[index] += 1;
            index = guess.charAt(i) - '0';
            if (countN[index] > 0) {
                y++;
            }
            countN[index] -= 1;
        }
        return new StringBuilder().append(x).append('A').append(y).append('B').toString();
    }

    public String getHint2(String secret, String guess) {
        int length = secret.length();

        int x = 0;
        int[] s = new int[length];
        int[] g = new int[length];
        int index = 0;
        for (int i = 0; i < length; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                x++;
                continue;
            }
            s[index] = secret.charAt(i) - '0';
            g[index] = guess.charAt(i) - '0';
            index++;
        }

        Arrays.sort(s, 0, index);
        Arrays.sort(g, 0, index);

        int sIndex = 0, gIndex = 0, y = 0;
        while (sIndex < index && gIndex < index) {
            if (s[sIndex] == g[gIndex]) {
                y++;
                sIndex++;
                gIndex++;
            } else if (s[sIndex] < g[gIndex]) {
                sIndex++;
            } else {
                gIndex++;
            }
        }
        return new StringBuilder().append(x).append('A').append(y).append('B').toString();
    }
}
