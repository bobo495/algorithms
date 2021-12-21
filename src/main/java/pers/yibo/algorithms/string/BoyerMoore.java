package pers.yibo.algorithms.string;

import java.util.Arrays;

/**
 * Boyer-Moore 字符串匹配算法（启发式地处理不匹配的字符）
 * <p>
 * 在一般情况下，对于长度为 N 的文本和长度为 M 的模式字符串， 使用了 Boyer-Moore 的子字 符串查找算法通过启发式处理不匹配的字符需要 ~N/M 次字符比较。
 *
 * @author yibo
 * @date 2021-12-21 11:31
 **/
public class BoyerMoore {
    /**
     * 字符集大小
     */
    private final int radix;

    /**
     * 需要跳过的长度
     */
    private final int[] right;

    /**
     * 存储匹配字符串
     */
    private final char[] pattern;

    /**
     * 输入pattern构造right数组
     *
     * @param pattern 待匹配字符串
     */
    public BoyerMoore(String pattern) {
        // 默认为ascii字符集
        this.radix = 256;
        this.pattern = pattern.toCharArray();
        this.right = new int[radix];
        initRight();
    }

    /**
     * 输入pattern构造right数组
     *
     * @param pattern 待匹配字符串
     * @param radix   字符集大小
     */
    public BoyerMoore(String pattern, int radix) {
        this.radix = radix;
        this.pattern = pattern.toCharArray();
        this.right = new int[radix];
        initRight();
    }

    /**
     * 初始化right数组：不存在的字符为-1，存在的字符为最大索引长度
     */
    private void initRight() {
        // 初始化right值为-1,表示未匹配上
        Arrays.fill(this.right, -1);

        // 记录每个字符在pattern中的最大索引
        for (int j = 0; j < pattern.length; j++) {
            right[pattern[j]] = j;
        }
    }

    /**
     * 返回符合条件的子字符串起始下标
     *
     * @param text 待搜索字符串
     * @return 找到了则返回起始下标，否则返回-1
     */
    public int search(String text) {
        int m = pattern.length;
        int n = text.length();
        // 跳跃索引长度
        int skip;
        // text从左向右移动
        for (int i = 0; i <= n - m; i += skip) {
            skip = 0;
            // pattern从右向左移动
            for (int j = m - 1; j >= 0; j--) {
                if (pattern[j] != text.charAt(i + j)) {
                    // 至少跳过1个，对于pattern中不存在的字符，则跳过j+1个
                    skip = Math.max(1, j - right[text.charAt(i + j)]);
                    break;
                }
            }
            if (skip == 0) {
                // 没有跳过则匹配成功
                return i;
            }
        }
        // 匹配失败返回-1
        return -1;
    }

    public static void main(String[] args) {
        BoyerMoore kmp = new BoyerMoore("ababababa");
        System.out.println(kmp.search("aabbababababaad"));
        BoyerMoore kmp2 = new BoyerMoore("dasgwgadg");
        System.out.println(kmp2.search("qgwegdasgwgadgvasdv"));
    }
}
