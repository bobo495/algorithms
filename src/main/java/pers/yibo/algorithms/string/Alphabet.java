package pers.yibo.algorithms.string;

import java.util.Arrays;

/**
 * 字母表
 * <p>
 * 上限为65,536，即0xFFFF
 *
 * @author yibo
 * @date 2021-12-09 10:03
 **/
public class Alphabet {

    /**
     * The binary alphabet { 0, 1 }.
     */
    public static final Alphabet BINARY = new Alphabet("01");

    /**
     * The octal alphabet { 0, 1, 2, 3, 4, 5, 6, 7 }.
     */
    public static final Alphabet OCTAL = new Alphabet("01234567");

    /**
     * The decimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 }.
     */
    public static final Alphabet DECIMAL = new Alphabet("0123456789");

    /**
     * The hexadecimal alphabet { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, A, B, C, D, E, F }.
     */
    public static final Alphabet HEXADECIMAL = new Alphabet("0123456789ABCDEF");

    /**
     * The DNA alphabet { A, C, T, G }.
     */
    public static final Alphabet DNA = new Alphabet("ACGT");

    /**
     * The lowercase alphabet { a, b, c, ..., z }.
     */
    public static final Alphabet LOWERCASE = new Alphabet("abcdefghijklmnopqrstuvwxyz");

    /**
     * The uppercase alphabet { A, B, C, ..., Z }.
     */

    public static final Alphabet UPPERCASE = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZ");

    /**
     * The protein alphabet { A, C, D, E, F, G, H, I, K, L, M, N, P, Q, R, S, T, V, W, Y }.
     */
    public static final Alphabet PROTEIN = new Alphabet("ACDEFGHIKLMNPQRSTVWY");

    /**
     * The base-64 alphabet (64 characters).
     */
    public static final Alphabet BASE64 = new Alphabet("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/");

    /**
     * The ASCII alphabet (0-127).
     */
    public static final Alphabet ASCII = new Alphabet(128);

    /**
     * The extended ASCII alphabet (0-255).
     */
    public static final Alphabet EXTENDED_ASCII = new Alphabet(256);

    /**
     * The Unicode 16 alphabet (0-65,535).
     */
    public static final Alphabet UNICODE16 = new Alphabet(65536);

    /**
     * 字符集
     */
    private final char[] alphabet;

    /**
     * 索引
     * <p>
     * inverse[c]表示，字符c在alphabet的下标
     */
    private final int[] inverse;

    /**
     * 字母表基数，即包含多少个字符
     */
    private final int radix;

    /**
     * 通过给定字符集初始化字母表
     *
     * @param alpha 给定字符集
     */
    public Alphabet(String alpha) {
        // 用于确定字符集不包含重复字符
        boolean[] unicode = new boolean[Character.MAX_VALUE];
        for (int i = 0; i < alpha.length(); i++) {
            char c = alpha.charAt(i);
            if (unicode[c]) {
                throw new IllegalArgumentException("Illegal alphabet: repeated character = '" + c + "'");
            }
            unicode[c] = true;
        }
        this.alphabet = alpha.toCharArray();
        this.radix = alpha.length();
        this.inverse = new int[Character.MAX_VALUE];
        // 初始化索引为-1，表示不存在
        Arrays.fill(this.inverse, -1);
        // 初始化每个字符的索引
        for (int i = 0; i < radix; i++) {
            this.inverse[alphabet[i]] = i;
        }
    }

    /**
     * 按unicode字符顺序初始化前{@code radix}个字符
     *
     * @param radix 基数
     */
    private Alphabet(int radix) {
        this.radix = radix;
        this.alphabet = new char[radix];
        this.inverse = new int[radix];

        for (int i = 0; i < radix; i++) {
            this.alphabet[i] = (char) i;
            this.inverse[i] = i;
        }
    }

    /**
     * 默认字母表为256个ascii字符集
     */
    public Alphabet() {
        this(256);
    }

    /**
     * 判断字符c是否在字母表中
     *
     * @param c 字符
     * @return true-在，false-不在
     */
    public boolean contains(char c) {
        return inverse[c] != -1;
    }

    /**
     * 获取字母表的基数
     *
     * @return {@code radix}, 字母表基数
     */
    public int radix() {
        return this.radix;
    }

    /**
     * 该字母表表达一个索引所需要的bit数
     *
     * @return 该字母表表达一个索引所需要的bit数
     */
    public int lgRadix() {
        int lgRadix = 0;
        for (int t = this.radix - 1; t >= 1; t /= 2) {
            lgRadix++;
        }
        return lgRadix;
    }

    /**
     * 返回字符c在字母表中的索引
     *
     * @param c 字符
     * @return 字符c对应的索引
     */
    public int toIndex(char c) {
        if (c >= this.inverse.length || this.inverse[c] == -1) {
            throw new IllegalArgumentException("Character " + c + " not in alphabet");
        }
        return this.inverse[c];
    }

    /**
     * 字符串转换为索引列表
     *
     * @param s 给定字符串
     * @return 索引列表
     */
    public int[] toIndices(String s) {
        int[] indices = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            indices[i] = toIndex(s.charAt(i));
        }
        return indices;
    }

    /**
     * 索引转换为字符
     *
     * @param index 给定索引
     * @return 索引index对应字符
     */
    public char toChar(int index) {
        if (index < 0 || index >= this.radix) {
            throw new IllegalArgumentException("index must be between 0 and " + this.radix + ": " + index);
        }
        return this.alphabet[index];
    }

    /**
     * 索引列表转换为字符串
     *
     * @param indices 索引列表
     * @return 索引列表indices对应字符串
     */
    public String toChars(int[] indices) {
        StringBuilder builder = new StringBuilder(indices.length);
        for (int index : indices) {
            builder.append(toChar(index));
        }
        return builder.toString();
    }


    /**
     * Unit tests the {@code Alphabet} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        int[] encoded1 = Alphabet.BASE64.toIndices("NowIsTheTimeForAllGoodMen");
        String decoded1 = Alphabet.BASE64.toChars(encoded1);
        System.out.println(decoded1);

        int[] encoded2 = Alphabet.DNA.toIndices("AACGAACGGTTTACCCCG");
        String decoded2 = Alphabet.DNA.toChars(encoded2);
        System.out.println(decoded2);

        int[] encoded3 = Alphabet.DECIMAL.toIndices("01234567890123456789");
        String decoded3 = Alphabet.DECIMAL.toChars(encoded3);
        System.out.println(decoded3);
    }
}
