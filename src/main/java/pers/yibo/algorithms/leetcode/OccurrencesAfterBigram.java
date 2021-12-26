package pers.yibo.algorithms.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 1078. Bigram 分词
 * <p>
 * 给出第一个词first 和第二个词second，考虑在某些文本text中可能以 "first second third" 形式出现的情况，其中second紧随first出现，third紧随second出现。
 * <p>
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 * <p>
 * https://leetcode-cn.com/problems/occurrences-after-bigram/
 *
 * @author yibo
 * 2021/12/26 17:45
 */
public class OccurrencesAfterBigram {
    public String[] findOcurrences(String text, String first, String second) {
        List<String> ans = new ArrayList<>();
        String[] arr = text.split(" ", -1);

        boolean firstCompare = false;
        boolean secondCompare = false;

        for (String a : arr) {
            if (secondCompare) {
                ans.add(a);
            }
            if (!a.equals(first)) {
                if (firstCompare && a.equals(second)) {
                    secondCompare = true;
                    firstCompare = false;
                } else {
                    firstCompare = false;
                    secondCompare = false;
                }
            } else {
                secondCompare = firstCompare && a.equals(second);
                firstCompare = true;
            }
        }
        return ans.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String text = "ypkk lnlqhmaohv lnlqhmaohv lnlqhmaohv ypkk ypkk ypkk ypkk ypkk ypkk lnlqhmaohv lnlqhmaohv lnlqhmaohv lnlqhmaohv ypkk ypkk ypkk lnlqhmaohv lnlqhmaohv ypkk";
        String first = "lnlqhmaohv";
        String second = "ypkk";

        OccurrencesAfterBigram o = new OccurrencesAfterBigram();
        System.out.println(Arrays.toString(o.findOcurrences(text, first, second)));
    }
}
