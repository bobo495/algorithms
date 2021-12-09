package pers.yibo.algorithms.leetcode;

/**
 * 794. 有效的井字游戏
 * <p>
 * https://leetcode-cn.com/problems/valid-tic-tac-toe-state
 * <p>
 * 用字符串数组作为井字游戏的游戏板board。当且仅当在井字游戏过程中，玩家有可能将字符放置成游戏板所显示的状态时，才返回 true。
 * <p>
 * 该游戏板是一个 3 x 3 数组，由字符" "，"X"和"O"组成。字符" "代表一个空位。
 * <p>
 * 玩家轮流将字符放入空位（" "）中。
 * 第一个玩家总是放字符 “X”，且第二个玩家总是放字符 “O”。
 * “X” 和 “O” 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 当所有位置非空时，也算为游戏结束。
 * 如果游戏结束，玩家不允许再放置字符。
 *
 * @author yibo
 * @date 2021-12-09 09:12
 **/
public class ValidTicTacToeState {
    public boolean validTicTacToe(String[] board) {
        int countX = 0;
        int countO = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i].charAt(j) == 'X') {
                    countX++;
                } else if (board[i].charAt(j) == 'O') {
                    countO++;
                }
            }
        }

        // x和o的数量相等或x多一个
        if (!(countX == countO || countX == countO + 1)) {
            return false;
        }

        // 判断结束状态，两个都有结束状态说明不可能
        boolean checkX = checkEnd(board, 'X');
        boolean checkO = checkEnd(board, 'O');
        if (checkX && checkO) {
            // 两个都有结束的
            return false;
        } else if (checkX && countX == countO) {
            // x结束了，但是o还下了一个
            return false;
        } else {
            // o结束了，x还下了一个
            return !checkO || countX != countO + 1;
        }
    }

    public boolean checkEnd(String[] board, char c) {

        for (int i = 0; i < 3; i++) {
            // 行
            if (board[i].charAt(0) == c && board[i].charAt(1) == c && board[i].charAt(2) == c) {
                return true;
            }
            // 列
            if (board[0].charAt(i) == c && board[1].charAt(i) == c && board[2].charAt(i) == c) {
                return true;
            }
        }

        // 对角线
        return (board[0].charAt(0) == c && board[1].charAt(1) == c && board[2].charAt(2) == c) || (board[0].charAt(2) == c && board[1].charAt(1) == c && board[2].charAt(0) == c);
    }
}
