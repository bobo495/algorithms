package pers.yibo.algorithms.leetcode;

/**
 * 6. Z 字形变换
 * <p>
 * https://leetcode-cn.com/problems/zigzag-conversion/
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * 解释：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 *
 * @author yibo
 * @date 2021-09-26 14:43
 **/
public class ZigzagConversion {
    public String convert(String s, int numRows) {
        if (numRows == 1) {
            return s;
        }

        StringBuilder out = new StringBuilder();
        int maxInterval = (numRows - 1) * 2;
        for (int i = 0; i < numRows; i++) {
            int interval = (numRows - i - 1) * 2;
            if (interval == 0) {
                interval = maxInterval;
            }
            for (int j = i; j < s.length(); ) {
                out.append(s.charAt(j));
                j += interval;
                if (interval != maxInterval) {
                    interval = maxInterval - interval;
                }
            }
        }
        return out.toString();
    }

    public static void main(String[] args) {
        ZigzagConversion z = new ZigzagConversion();
        System.out.println(z.convert("A", 1));
    }
}
