package pers.yibo.algorithms.string;

import java.util.Arrays;

/**
 * KMP算法查找子字符串
 * <p>
 * 对于长度为 M 的模式字符串和长度为 N 的文本，Knuth-Morris-Pratt 字符串查找算法访 问的字符不会超过 M+N 个
 *
 * @author yibo
 * @date 2021-12-20 17:29
 **/
public class KnuthMorrisPratt {
    /**
     * 字符集大小
     */
    private final int radix;
    /**
     * 匹配Pattern的长度
     */
    private final int patternLength;

    /**
     * 确定有限状态自动机 - Deterministic Finite Automaton
     */
    private final int[][] dfa;

    /**
     * 输入pattern构造dfa
     *
     * @param pattern 待匹配字符串
     */
    public KnuthMorrisPratt(String pattern) {
        // 默认为ascii字符集
        this.radix = 256;
        this.patternLength = pattern.length();
        this.dfa = new int[radix][patternLength];
        // 构造DFA
        initDfa(pattern);
    }

    /**
     * 输入pattern构造dfa
     *
     * @param pattern 待匹配字符串
     * @param radix   字符集大小
     */
    public KnuthMorrisPratt(String pattern, int radix) {
        this.radix = radix;
        this.patternLength = pattern.length();
        this.dfa = new int[radix][patternLength];
        // 构造DFA
        initDfa(pattern);
    }

    /**
     * 初始化dfa
     * <p>
     * 1. 匹配失败时：将 dfa[][X] 复制到 dfa[][j]（回退到x，即pattern[0-x]为重复字段）
     * <p>
     * 2. 匹配成功时：将 dfa[pat.charAt(j)][j] 设为 j+1
     * <p>
     * 3. 更新x：x的第一次更新在与pattern[0]重复的位置，随着x增加，说明当前子字符串与pattern[0]开始的子字符串相同
     * <p>
     * 例如ABABAC，当j=5时，匹配到了ABABAB，则仅需回退到j=3的地方(保留ABAB)，此时x=3，即将x=3的结果复制到当前值
     *
     * @param pattern 待匹配字符串
     */
    private void initDfa(String pattern) {
        // 初始化第一个字符
        dfa[pattern.charAt(0)][0] = 1;
        for (int x = 0, j = 1; j < patternLength; j++) {
            // 不匹配的情况
            for (int c = 0; c < radix; c++) {
                dfa[c][j] = dfa[c][x];
            }
            // 匹配的情况
            dfa[pattern.charAt(j)][j] = j + 1;
            // 更新重启状态
            int before = x;
            x = dfa[pattern.charAt(j)][x];
            System.out.println("j : " + j + " , x before : " + before + " , after : " + x);
        }
        System.out.println("======== dfa: " + pattern + " =========");
        for (int i = 0; i < this.radix; i++) {
            for (int n : dfa[i]) {
                if (n != 0) {
                    System.out.println(((char) i) + " " + Arrays.toString(dfa[i]));
                    break;
                }
            }
        }
        System.out.println("============= end ===============");
    }

    /**
     * 返回符合条件的子字符串起始下标
     *
     * @param text 待搜索字符串
     * @return 找到了则返回起始下标，否则返回-1
     */
    public int search(String text) {
        int i, j;
        for (i = 0, j = 0; i < text.length() && j < patternLength; i++) {
            j = dfa[text.charAt(i)][j];
        }

        return j == patternLength ? i - patternLength : -1;
    }

    /**
     * char数组中查找子字符串
     *
     * @param text 待搜索字符数组
     * @return 找到了则返回起始下标，否则返回-1
     */
    public int search(char[] text) {
        int i, j;
        for (i = 0, j = 0; i < text.length && j < patternLength; i++) {
            j = dfa[text[i]][j];
        }
        return j == patternLength ? i - patternLength : -1;
    }

    public static void main(String[] args) {
        KnuthMorrisPratt kmp4 = new KnuthMorrisPratt("abcabdabe");
        KnuthMorrisPratt kmp3 = new KnuthMorrisPratt("ababac");
        KnuthMorrisPratt kmp = new KnuthMorrisPratt("ababababa");
        System.out.println(kmp.search("aabbababababaad"));
        KnuthMorrisPratt kmp2 = new KnuthMorrisPratt("dasgwgadg");
        System.out.println(kmp2.search("qgwegdasgwgadgvasdv"));
    }
}
