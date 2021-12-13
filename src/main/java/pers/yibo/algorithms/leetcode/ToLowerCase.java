package pers.yibo.algorithms.leetcode;

/**
 * 709. 转换成小写字母
 * <p>
 * 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 *
 * @author yibo
 * 2021/12/12 16:39
 */
public class ToLowerCase {
    public String toLowerCase(String s) {
        StringBuilder builder = new StringBuilder(s.length());
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                builder.append((char) (c + 32));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }
}
