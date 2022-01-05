package pers.yibo.algorithms.leetcode;

/**
 * 1576. 替换所有的问号
 *
 * @author yibo
 * @date 2022-01-05 09:29
 **/
public class ModifyString1576 {
    public String modifyString(String s) {
        StringBuilder ans = new StringBuilder();
        char left = 0;
        char right;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '?') {
                if (i == s.length() - 1) {
                    right = 'z';
                } else {
                    right = s.charAt(i + 1);
                }
                if (left >= 'c' || left < 'a') {
                    if (right == 'c' || right == 'a') {
                        ans.append('b');
                    } else {
                        ans.append('a');
                    }
                } else {
                    if (left == 'a') {
                        if (right == 'c') {
                            ans.append('d');
                        } else {
                            ans.append('c');
                        }
                    } else {
                        if (right == 'd') {
                            ans.append('f');
                        } else {
                            ans.append('d');
                        }
                    }
                }
                left = ans.charAt(i);
            } else {
                ans.append(s.charAt(i));
                left = s.charAt(i);
            }
        }
        return ans.toString();
    }
}
