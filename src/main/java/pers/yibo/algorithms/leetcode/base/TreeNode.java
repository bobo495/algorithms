package pers.yibo.algorithms.leetcode.base;

/**
 * 二叉树节点
 *
 * @author yibo
 * 2022/2/26 13:32
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
