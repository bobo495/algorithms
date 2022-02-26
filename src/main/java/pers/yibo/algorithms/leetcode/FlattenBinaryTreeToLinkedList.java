package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.TreeNode;

/**
 * 114. 二叉树展开为链表
 * <p>
 * https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/
 *
 * @author yibo
 * 2022/2/26 13:32
 */
public class FlattenBinaryTreeToLinkedList {
    public void flatten(TreeNode root) {
        dfs(root);
    }

    /**
     * dfs 有左节点，则左节点置空，右节点为左节点子树，左节点为父节点的右节点
     */
    public void dfs(TreeNode node) {
        if (node == null) {
            return;
        }

        // 左节点为空时，遍历右节点
        if (node.left == null) {
            dfs(node.right);
            return;
        }

        // 遍历左节点
        dfs(node.left);

        // 右节点添加到左节点的最右
        moveRight(node);

        // 左节点修正完成后，修正右节点
        dfs(node.right);
    }

    public void moveRight(TreeNode node) {
        if (node.left == null && node.right == null) {
            return;
        }
        // 左节点尾部
        TreeNode leftTail = node.left;
        while (leftTail.right != null) {
            leftTail = leftTail.right;
        }

        TreeNode right = node.right;
        // 左节点迁移到右节点
        node.right = node.left;
        // 右节点接到左节点尾部
        leftTail.right = right;
        // 左节点置空
        node.left = null;
    }
}
