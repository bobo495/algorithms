package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 488. 祖玛游戏
 * <p>
 * https://leetcode-cn.com/problems/zuma-game
 * <p>
 * 输入：board = "WRRBBW", hand = "RB"
 * 输出：-1
 * 解释：无法移除桌面上的所有球。可以得到的最好局面是：
 * - 插入一个 'R' ，使桌面变为 WRRRBBW 。WRRRBBW -> WBBW
 * - 插入一个 'B' ，使桌面变为 WBBBW 。WBBBW -> WW
 * 桌面上还剩着球，没有其他球可以插入。
 * <p>
 * 输入：board = "WWRRBBWW", hand = "WRBRW"
 * 输出：2
 * 解释：要想清空桌面上的球，可以按下述步骤：
 * - 插入一个 'R' ，使桌面变为 WWRRRBBWW 。WWRRRBBWW -> WWBBWW
 * - 插入一个 'B' ，使桌面变为 WWBBBWW 。WWBBBWW -> WWWW -> empty
 * 只需从手中出 2 个球就可以清空桌面。
 *
 * @author yibo
 * @date 2021-11-09 09:06
 **/
public class ZumaGame {
    public int findMinStep(String board, String hand) {
        Map<String, String> map = new HashMap<>(1);
        map.put(board, hand);
        return nextStep(map, 0);
    }

    /**
     * 广序遍历，每次将球插入至相同颜色球的位置
     */
    public int nextStep(Map<String, String> map, int steps) {
        // 下一步所有boarderBuilder及handBuilder
        Map<String, String> nextMap = new HashMap<>(16);

        for (Map.Entry<String, String> entry : map.entrySet()) {
            String board = entry.getKey();
            String hand = entry.getValue();

            Set<Character> history = new HashSet<>();
            for (int j = 0; j < hand.length(); j++) {
                char h = hand.charAt(j);
                if (history.contains(h)) {
                    continue;
                }
                char lastChar = ' ';
                for (int i = 0; i < board.length(); i++) {
                    char c = board.charAt(i);
                    if (c == h && c == lastChar) {
                        continue;
                    }
                    String copyHand = new StringBuilder(hand).deleteCharAt(j).toString();
                    StringBuilder copyBoardBuilder = new StringBuilder(board);
                    copyBoardBuilder.insert(i, h);
                    if (i < board.length() - 1 && c == board.charAt(i + 1)) {
                        String result = eliminate(copyBoardBuilder, i, c).toString();
                        if (result.length() == 0) {
                            return steps + 1;
                        }
                        // 加一个新的后，可以消除
                        nextMap.put(result, copyHand);
                    } else {
                        // 加一个新的后，不能消除
                        nextMap.put(copyBoardBuilder.toString(), copyHand);
                    }
                    lastChar = c;
                }
                history.add(h);
            }
        }
        if (nextMap.isEmpty()) {
            return -1;
        } else {
            return nextStep(nextMap, steps + 1);
        }
    }

    public StringBuilder eliminate(StringBuilder boardBuilder, int index, char color) {
        while (index < boardBuilder.length() && boardBuilder.charAt(index) == color) {
            boardBuilder.deleteCharAt(index);
        }
        if (index == boardBuilder.length()) {
            return boardBuilder;
        }

        // 判断是否还存在可消除颜色
        char nextColor = boardBuilder.charAt(index);
        int count = 1;
        int leftIndex = index;
        for (int i = index - 1; i >= 0; i--) {
            if (boardBuilder.charAt(i) != nextColor) {
                break;
            }
            leftIndex = i;
            count++;
        }
        if (count >= 3) {
            return eliminate(boardBuilder, leftIndex, nextColor);
        }

        for (int i = index + 1; i < boardBuilder.length(); i++) {
            if (boardBuilder.charAt(i) != nextColor) {
                break;
            }
            count++;
        }

        if (count >= 3) {
            return eliminate(boardBuilder, leftIndex, nextColor);
        } else {
            return boardBuilder;
        }
    }

    public static void main(String[] args) {
        ZumaGame z = new ZumaGame();
        String board = "BRBWRWWGGWWYY";
        String hand = "YGBWY";
        long start = System.currentTimeMillis();
        System.out.println(z.findMinStep(board, hand));
        System.out.println((System.currentTimeMillis() - start));
    }
}
