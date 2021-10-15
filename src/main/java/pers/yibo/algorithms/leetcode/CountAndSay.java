package pers.yibo.algorithms.leetcode;

/**
 * 38. 外观数列
 * <p>
 * https://leetcode-cn.com/problems/count-and-say
 * <p>
 * 给定一个正整数 n ，输出外观数列的第 n 项。
 * <p>
 * 「外观数列」是一个整数序列，从数字 1 开始，序列中的每一项都是对前一项的描述。
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 第一项是数字 1
 * 描述前一项，这个数是 1 即 “ 一 个 1 ”，记作 "11"
 * 描述前一项，这个数是 11 即 “ 二 个 1 ” ，记作 "21"
 * 描述前一项，这个数是 21 即 “ 一 个 2 + 一 个 1 ” ，记作 "1211"
 * 描述前一项，这个数是 1211 即 “ 一 个 1 + 一 个 2 + 二 个 1 ” ，记作 "111221"
 *
 * @author yibo
 * @date 2021-10-15 16:19
 **/
public class CountAndSay {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return sayLast(countAndSay(n - 1));
    }

    public String sayLast(String last) {
        StringBuilder builder = new StringBuilder();
        char num = 0;
        int count = 0;
        for (char c : last.toCharArray()) {
            if (count == 0) {
                num = c;
                count++;
                continue;
            }

            if (num != c) {
                builder.append(count).append(num);
                num = c;
                count = 1;
                continue;
            }
            count++;
        }
        return builder.append(count).append(num).toString();
    }

    public static void main(String[] args) {
        CountAndSay c = new CountAndSay();
        System.out.println(c.countAndSay(4));
    }
}
