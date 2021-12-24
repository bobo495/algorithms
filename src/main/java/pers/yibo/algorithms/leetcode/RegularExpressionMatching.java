package pers.yibo.algorithms.leetcode;

/**
 * 10. 正则表达式匹配
 * <p>
 * https://leetcode-cn.com/problems/regular-expression-matching/
 *
 * @author yibo
 * @date 2021-09-26 16:03
 **/
public class RegularExpressionMatching {

    private static class Node {
        int w;
        Node next;
    }

    public boolean isMatch(String s, String p) {

        int v = p.length() + 1;
        // 邻接链表记录下一步的去向
        Node[] adjList = new Node[v];
        // 初始化邻接链表：找到*，新增*到前一个和后一个节点的边
        for (int i = 0; i < p.length(); i++) {
            if (p.charAt(i) == '*') {
                // * 到下一个节点
                adjList[i] = addNode(adjList[i], i + 1);
                // * 到上一个节点
                adjList[i] = addNode(adjList[i], i - 1);
                // 上一个节点到 *
                adjList[i - 1] = addNode(adjList[i], i);
            }
        }

        // 下一步可达
        boolean[] marked = new boolean[v];
        // 初始化
        dfs(adjList, marked, 0);

        for (int i = 0; i < s.length(); i++) {
            // 从下一步可达中匹配
            boolean[] match = new boolean[v];
            for (int j = 0; j < v - 1; j++) {
                if (!marked[j]) {
                    continue;
                }
                // 下一步匹配
                if (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.') {
                    match[j + 1] = true;
                }
            }

            boolean hasNext = false;
            // 重置marked
            marked = new boolean[v];
            // 多点匹配
            for (int j = 0; j < v; j++) {
                if (match[j]) {
                    dfs(adjList, marked, j);
                    hasNext = true;
                }
            }

            if (!hasNext) {
                return false;
            }
        }

        // 接受状态是否可达
        return marked[v - 1];
    }

    private void dfs(Node[] adjList, boolean[] marked, int s) {
        if (marked[s]) {
            return;
        }
        marked[s] = true;
        Node adj = adjList[s];
        while (adj != null) {
            if (!marked[adj.w]) {
                dfs(adjList, marked, adj.w);
            }
            adj = adj.next;
        }
    }

    private Node addNode(Node node, int w) {
        if (node == null) {
            node = new Node();
            node.w = w;
            return node;
        } else {
            node.next = addNode(node.next, w);
        }
        return node;
    }

    public static void main(String[] args) {
        RegularExpressionMatching r = new RegularExpressionMatching();
//        System.out.println(r.isMatch("mississippi", "m*is*is*ip*."));

        System.out.println(r.isMatch("aaa", "ab*a*c*a"));
//        System.out.println(r.isMatch("bbbba", ".*a*a*"));
//        System.out.println(r.isMatch("aasdfasdfasdfasdfas", "aasdf.*asdf.*asdf.*asdf.*s"));
//        System.out.println(r.isMatch("abbbcd", "ab*bbbcd"));
//        System.out.println(r.isMatch("bbba", ".*b"));
//        System.out.println(r.isMatch("abcaaaaaaabaabcabac", ".*ab.a.*a*a*.*b*b*"));
//        System.out.println(r.isMatch("aa", "."));
//        System.out.println(r.isMatch("a", ".*..a*"));
//        System.out.println(r.isMatch("ab", ".*.."));
//        System.out.println(r.isMatch("acaabbaccbbacaabbbb", "a*.*b*.*a*aa*a*"));
    }
}
