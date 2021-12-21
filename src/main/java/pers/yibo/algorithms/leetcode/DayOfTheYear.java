package pers.yibo.algorithms.leetcode;

/**
 * 1154. 一年中的第几天
 * <p>
 * https://leetcode-cn.com/problems/day-of-the-year
 * <p>
 * 给你一个字符串date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。请你计算并返回该日期是当年的第几天。
 * <p>
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 *
 * @author yibo
 * @date 2021-12-21 09:08
 **/
public class DayOfTheYear {
    public int dayOfYear(String date) {

        int month = Integer.parseInt(date.substring(5, 7));
        int day = Integer.parseInt(date.substring(8, 10));

        int[] monthDay = new int[]{0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};

        int year = Integer.parseInt(date.substring(0, 4));
        return monthDay[month - 1] + day +
                ((month > 2) && (year % 400 == 0 || (year % 4 == 0 && year / 100 != 0)) ? 1 : 0);
    }


}
