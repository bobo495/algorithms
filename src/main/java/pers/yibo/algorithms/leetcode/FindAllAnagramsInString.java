package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 438. 找到字符串中所有字母异位词
 * <p>
 * https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
 * <p>
 * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 * <p>
 * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
 *
 * @author aoyb
 * 2021/11/28 10:11
 */
public class FindAllAnagramsInString {
    public List<Integer> findAnagrams(String s, String p) {
        int markP = 0;
        for (int i = 0; i < p.length(); i++) {
            markP += 1 << ('z' - p.charAt(i));
        }
        List<Integer> out = new ArrayList<>();

        int left = 0;
        int markS = 0;
        for (int i = 0; i < s.length(); i++) {
            if (i < p.length()) {
                markS += 1 << ('z' - s.charAt(i));
            } else {
                if (markS == markP) {
                    out.add(left);
                }
                markS += 1 << ('z' - s.charAt(i));
                markS -= 1 << ('z' - s.charAt(left));
                left++;
            }
        }

        // 最后一位
        if (markS == markP) {
            out.add(left);
        }

        return out;
    }

    public static void main(String[] args) {
        FindAllAnagramsInString f = new FindAllAnagramsInString();
        String s = "cbaebabacd";
        String p = "abc";
        System.out.println(f.findAnagrams(s, p));
    }
}
