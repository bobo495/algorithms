package pers.yibo.algorithms.leetcode;

import java.util.List;

/**
 * 559. N 叉树的最大深度
 * <p>
 * https://leetcode-cn.com/problems/maximum-depth-of-n-ary-tree
 * <p>
 * 给定一个 N 叉树，找到其最大深度。
 * <p>
 * 最大深度是指从根节点到最远叶子节点的最长路径上的节点总数。
 * <p>
 * N 叉树输入按层序遍历序列化表示，每组子节点由空值分隔（请参见示例）。
 *
 * @author aoyb
 * 2021/11/21 12:04
 */
public class MaximumDepthOfNAryTree {
    /**
     * DFS
     */
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }

        int max = 1;
        for (Node child : root.children) {
            max = Math.max(max, 1 + maxDepth(child));
        }
        return max;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
