package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 10. 正则表达式匹配
 * <p>
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * @author yibo
 * @date 2021-09-26 14:43
 **/
public class RegularExpressionMatching {

    public boolean isMatch(String s, String p) {
        char lastP = p.charAt(p.length() - 1);
        if (lastP != '*' && lastP != '.' && lastP != s.charAt(s.length() - 1)) {
            return false;
        }

        int indexP = 0;

        // 移除末尾的点
        while (p.charAt(p.length() - 1) == '.') {
            s = s.substring(0, s.length() - 1);
            p = p.substring(0, p.length() - 1);
            if (p.isEmpty()) {
                return s.isEmpty();
            }
            if (s.isEmpty() && p.length() >= 2) {
                for (int i = indexP + 1; i < p.length(); i += 2, indexP += 2) {
                    if (p.charAt(i) != '*') {
                        return false;
                    }
                }
                return indexP == p.length();
            }
        }

        int multiMatchCount = 0;
        List<Integer> multiMatchIndexList = new ArrayList<>();


        for (char c : s.toCharArray()) {

            if (indexP >= p.length()) {
                return false;
            }

            if (p.charAt(indexP) == c || p.charAt(indexP) == '.') {
                if (indexP < p.length() - 1 && p.charAt(indexP + 1) == '*') {
                    multiMatchCount += 1;
                    p = removeDuplicatePattern(p, c, indexP);
                    multiMatchIndexList.clear();
                    multiMatchIndexList.add(indexP);
                    findMultiMatch(multiMatchIndexList, multiMatchCount, p, c);
                } else {
                    multiMatchCount = 0;
                    multiMatchIndexList.clear();
                    indexP += 1;
                }
            } else {
                while (true) {
                    if (indexP < p.length() - 2 && p.charAt(indexP + 1) == '*') {
                        if (p.charAt(indexP + 2) == c || p.charAt(indexP + 2) == '.') {
                            if (indexP < p.length() - 3 && p.charAt(indexP + 3) == '*') {
                                indexP += 2;
                                multiMatchCount += 1;
                                p = removeDuplicatePattern(p, c, indexP);
                                multiMatchIndexList.clear();
                                multiMatchIndexList.add(indexP);
                                findMultiMatch(multiMatchIndexList, multiMatchCount, p, c);
                            } else {
                                multiMatchCount = 0;
                                multiMatchIndexList.clear();
                                indexP += 3;
                            }
                            break;
                        } else {
                            indexP += 2;
                        }
                    } else {
                        return false;
                    }
                }
            }
        }
        System.out.println(indexP+" "+p);
        if (multiMatchCount != 0) {
            if (multiMatchIndexList.size() > s.length()) {
                return false;
            }
            indexP = multiMatchIndexList.get(multiMatchIndexList.size() - 1) + 1;
            if (indexP <= p.length() - 1 && p.charAt(indexP) == '*') {
                indexP += 1;
            }
        }

        // 移除可以为0的p
        for (int i = indexP + 1; i < p.length(); i += 2, indexP += 2) {
            if (p.charAt(i) != '*') {
                return false;
            }
        }

        return indexP == p.length();
    }

    public String removeDuplicatePattern(String p, char c, int indexP) {

        if (p.length() < indexP + 3 || (p.charAt(indexP + 2) != c && p.charAt(indexP + 2) != '.')) {
            return p;
        }

        // a*b，移除b .*b，移除b
        if (p.length() == indexP + 3) {
            return p.substring(0, indexP + 2);
        }


        if (p.charAt(indexP) == '.') {
            // .*b*，移除b*
            if (p.length() >= indexP + 4 && p.charAt(indexP + 3) == '*') {
                return deleteChars(p, indexP + 2, indexP + 3);
            }
            // .*b.*，移除b.*
            if (p.length() >= indexP + 5 && p.charAt(indexP + 3) == '.' && p.charAt(indexP + 4) == '*') {
                return deleteChars(p, indexP + 2, indexP + 4);
            }
        }

        // a*bb，移除b .*bb，移除b
        if (p.length() >= indexP + 4 && p.charAt(indexP + 3) != '*') {
            return p.substring(0, indexP + 2) + p.substring(indexP + 3);
        }

        // a*b.*，移除a*b
        if (p.length() >= indexP + 5 && p.charAt(indexP) == c && p.charAt(indexP + 3) == '.' && p.charAt(indexP + 4) == '*') {
            return deleteChars(p, indexP, indexP + 2);
        }

        return p;
    }

    public String deleteChars(String p, int indexStart, int indexEnd) {
        if (p.length() > indexEnd + 1) {
            return p.substring(0, indexStart) + p.substring(indexEnd + 1);
        } else {
            return p.substring(0, indexStart);
        }
    }

    public void findMultiMatch(List<Integer> multiMatchIndexList, int multiMatchCount, String p, char c) {
        int tmpIndexP = multiMatchIndexList.get(multiMatchIndexList.size() - 1) + 1;
        if (tmpIndexP < p.length() && p.charAt(tmpIndexP) == '*') {
            tmpIndexP += 1;
        }
        while (multiMatchIndexList.size() < multiMatchCount + 1) {
            if (tmpIndexP < p.length()) {
                if (p.charAt(tmpIndexP) == c || p.charAt(tmpIndexP) == '.') {
                    multiMatchIndexList.add(tmpIndexP);
                    if (tmpIndexP < p.length() - 2 && p.charAt(tmpIndexP + 1) == '*') {
                        tmpIndexP += 2;
                    } else {
                        tmpIndexP += 1;
                    }
                } else {
                    if (tmpIndexP < p.length() - 2 && p.charAt(tmpIndexP + 1) == '*') {
                        tmpIndexP += 2;
                    } else {
                        break;
                    }
                }
            } else {
                break;
            }
        }
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
        System.out.println(r.isMatch("aaba", "ab*a*c*a"));
        System.out.println(r.isMatch("bbbba", ".*a*a*"));
        System.out.println(r.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
        System.out.println(r.isMatch("abbbcd", "ab*bbbcd"));
        System.out.println(r.isMatch("bbba", ".*b"));
        System.out.println(r.isMatch("abcaaaaaaabaabcabac", ".*ab.a.*a*a*.*b*b*"));
        System.out.println(r.isMatch("mississippi", "mis*is*ip*."));
        System.out.println(r.isMatch("aa", "."));
        System.out.println(r.isMatch("a", ".*..a*"));
        System.out.println(r.isMatch("ab", ".*.."));
        System.out.println(r.isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*"));
    }
}
