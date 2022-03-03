package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 49. 字母异位词分组
 * <p>
 * https://leetcode-cn.com/problems/group-anagrams/
 *
 * @author yibo
 * 2022/3/3 14:54
 */
public class GroupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String sortStr = String.valueOf(arr);
            if (map.containsKey(sortStr)) {
                List<String> list = map.get(sortStr);
                list.add(str);
                map.put(sortStr, list);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(sortStr, list);
            }
        }
        return new ArrayList<>(map.values());
    }
}
