package pers.yibo.algorithms.leetcode;

import java.util.*;

/**
 * 211. 添加与搜索单词 - 数据结构设计
 * <p>
 * https://leetcode-cn.com/problems/design-add-and-search-words-data-structure
 * <p>
 * 请你设计一个数据结构，支持 添加新单词 和 查找字符串是否与任何先前添加的字符串匹配 。
 * <p>
 * 实现词典类 WordDictionary ：
 * <p>
 * WordDictionary() 初始化词典对象
 * void addWord(word) 将 word 添加到数据结构中，之后可以对它进行匹配
 * bool search(word) 如果数据结构中存在字符串与word 匹配，则返回 true ；否则，返回 false 。word 中可能包含一些 '.' ，每个. 都可以表示任何一个字母。
 * <p>
 * 输入：
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * 输出：
 * [null,null,null,null,false,true,true,true]
 * <p>
 * 解释：
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 * @author yibo
 * @date 2021-10-19 10:24
 **/
public class DesignAddAndSearchWordsDataStructure {


    static class WordDictionary {

        class Node {
            boolean isEnd;
            // 通过数组位置确认Next Char
            Node[] children = new Node[26];

        }

        Node root;

        public WordDictionary() {
            this.root = new Node();
        }

        public void addWord(String word) {
            Node tmp = root;
            for (int i = 0; i < word.length(); i++) {
                int index = word.charAt(i) - 'a';
                Node child = tmp.children[index] == null ? new Node() : tmp.children[index];
                if (i == word.length() - 1) {
                    child.isEnd = true;
                }
                tmp.children[index] = child;
                tmp = child;
            }
        }

        public boolean search(String word) {
            return checkChar(root, word, 0);
        }

        public boolean checkChar(Node node, String word, int index) {
            if (node == null) {
                return false;
            }
            char c = word.charAt(index);
            if (index == word.length() - 1) {
                if (c == '.') {
                    for (Node child : node.children) {
                        if (child != null && child.isEnd) {
                            return true;
                        }
                    }
                    return false;
                } else {
                    Node child = node.children[c - 'a'];
                    if (child == null) {
                        return false;
                    }
                    return child.isEnd;
                }
            }

            if (c == '.') {
                for (Node child : node.children) {
                    if (checkChar(child, word, index + 1)) {
                        return true;
                    }
                }
                return false;
            } else {
                return checkChar(node.children[c - 'a'], word, index + 1);
            }
        }
    }

    public static void main(String[] args) {
        WordDictionary w = new WordDictionary();
        w.addWord("add");
        System.out.println(w.search("add"));
    }

    /**
     * HashMap方法
     */
    class WordDictionary2 {

        Map<Integer, Set<String>> mapWords;

        public WordDictionary2() {
            mapWords = new HashMap<>(16);
        }

        public void addWord(String word) {
            Set<String> words = mapWords.getOrDefault(word.length(), new HashSet<>());
            words.add(word);
            mapWords.put(word.length(), words);
        }

        public boolean search(String word) {
            for (String w : mapWords.getOrDefault(word.length(), new HashSet<>())) {
                if (check(w, word)) {
                    return true;
                }
            }
            return false;
        }

        public boolean check(String word, String pattern) {
            if (word.length() != pattern.length()) {
                return false;
            }

            for (int i = 0; i < word.length(); i++) {
                if (pattern.charAt(i) != '.' && pattern.charAt(i) != word.charAt(i)) {
                    return false;
                }
            }
            return true;
        }
    }
}
