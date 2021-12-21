package pers.yibo.algorithms.string;

import java.math.BigInteger;
import java.util.Random;

/**
 * Rabin-Karp 指纹字符串查找算法
 * <p>
 * hash值校验
 *
 * @author yibo
 * @date 2021-12-21 14:29
 **/
public class RabinKarp {

    /**
     * pattern长度
     */
    private final int m;

    /**
     * pattern hash值
     */
    private final long patternHash;

    /**
     * 存储pattern值，hash相等时校验是否符合条件
     */
    private final String pattern;

    /**
     * 尽量大的质数，避免overflow
     */
    private final long prime;

    /**
     * 字符集大小
     */
    private final int radix;

    /**
     * radix ^ (m-1) % prime;
     */
    private long rm;

    public RabinKarp(String pattern) {
        // 默认为ascii字符集
        this.radix = 256;
        this.pattern = pattern;
        this.m = pattern.length();
        this.prime = longRandomPrime();

        // 计算rm
        this.rm = 1;
        for (int i = 1; i <= m - 1; i++) {
            this.rm = (this.rm * radix) % prime;
        }
        // 计算pattern hash
        this.patternHash = hash(pattern, m);
    }

    /**
     * 计算hash
     * <p>
     * Horner方法，用于除留余数法计算散列值
     *
     * @param key 待计算字符串
     * @param m   pattern长度
     * @return key对应的hash值
     */
    private long hash(String key, int m) {
        long hash = 0;
        for (int j = 0; j < m; j++) {
            hash = (radix * hash + key.charAt(j)) % prime;
        }
        return hash;
    }

    public int search(String text) {
        int n = text.length();
        if (n < m) {
            return -1;
        }

        // 计算text初始hash，前m位子字符串
        long textHash = hash(text, m);

        if (textHash == patternHash && check(text, 0)) {
            return 0;
        }

        for (int i = m; i < n; i++) {
            // 移除首位，并增加末位
            textHash = (textHash + prime - rm * text.charAt(i - m) % prime) % prime;
            textHash = (textHash * radix + text.charAt(i)) % prime;

            // 匹配校验
            int offset = i - m + 1;
            if (textHash == patternHash && check(text, offset)) {
                return offset;
            }
        }
        return -1;
    }


    private boolean check(String text, int i) {
        for (int j = 0; j < m; j++) {
            if (pattern.charAt(j) != text.charAt(i + j)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取随机31位质数
     *
     * @return 随机31位质数
     */
    private long longRandomPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    public static void main(String[] args) {
        RabinKarp kmp = new RabinKarp("ababababa");
        System.out.println(kmp.search("aabbababababaad"));
        RabinKarp kmp2 = new RabinKarp("dasgwgadg");
        System.out.println(kmp2.search("qgwegdasgwgadgvasdv"));
    }
}
