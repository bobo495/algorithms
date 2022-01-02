package pers.yibo.algorithms.leetcode;

/**
 * 390. 消除游戏
 * <p>
 * https://leetcode-cn.com/problems/elimination-game/
 *
 * @author yibo
 * 2022/1/2 18:28
 */
public class EliminationGame {
    public int lastRemaining(int n) {
        return next(1, n, 1, true);
    }

    private int next(int start, int end, int interval, boolean rightDirection) {
        System.out.println(start + " " + end + " " + interval);
        if (start >= end - interval) {
            if (rightDirection) {
                return end;
            } else {
                return start;
            }
        }
        if (rightDirection) {
            return next(start + interval, ((end - start) / interval % 2) != 0 ? end : (end - interval), interval + interval, false);
        } else {
            return next(((end - start) / interval % 2) != 0 ? start : (start + interval), end - interval, interval + interval, true);
        }
    }

    public static void main(String[] args) {
        EliminationGame e = new EliminationGame();
        System.out.println(e.lastRemaining(9));
        System.out.println(e.lastRemaining(3));
        System.out.println(e.lastRemaining(2));
        System.out.println(e.lastRemaining(6));
    }
}
