package pers.yibo.algorithms.leetcode;

/**
 * 1185. 一周中的第几天
 * <p>
 * https://leetcode-cn.com/problems/day-of-the-week/
 *
 * @author yibo
 * 2022/1/3 15:54
 */
public class DayOfTheWeek {
    /**
     * @see <a href="https://superlova.github.io/2021/04/16/%E3%80%90%E5%AD%A6%E4%B9%A0%E7%AC%94%E8%AE%B0%E3%80%91%E5%A6%82%E4%BD%95%E6%B1%82%E5%87%BA%E4%BB%BB%E6%84%8F%E6%97%A5%E6%9C%9F%E6%98%AF%E6%98%9F%E6%9C%9F%E5%87%A0%EF%BC%9F/">计算方法</a>
     */
    public String dayOfTheWeek(int day, int month, int year) {
        String[] week = new String[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        if (month < 3) {
            year -= 1;
            month += 12;
        }
        return week[(day + 2 * month + 3 * (month + 1) / 5 + year + year / 4 - year / 100 + year / 400) % 7];
    }
}
