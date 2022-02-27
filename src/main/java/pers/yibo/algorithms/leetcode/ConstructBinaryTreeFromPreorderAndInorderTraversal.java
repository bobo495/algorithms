package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.TreeNode;

/**
 * 105. 从前序与中序遍历序列构造二叉树
 * <p>
 * https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 *
 * @author yibo
 * 2022/2/27 11:34
 */
public class ConstructBinaryTreeFromPreorderAndInorderTraversal {

    /**
     * 先序遍历数组中的索引下标（从左子树开始构造，该下标对应子树的根节点）
     */
    int preOrderIndex = 0;

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int rootIndex = search(inorder, 0, inorder.length - 1, preorder[preOrderIndex]);
        // 每次构建节点后下标+1
        TreeNode root = new TreeNode(preorder[preOrderIndex++]);
        // 构建左子树，至少有一个节点
        if (rootIndex > 0) {
            buildSubTree(root, true, preorder, inorder, 0, rootIndex - 1);
        }
        // 构建右子树
        if (rootIndex < inorder.length - 1) {
            buildSubTree(root, false, preorder, inorder, rootIndex + 1, inorder.length - 1);
        }

        return root;
    }

    private int search(int[] inorder, int left, int right, int target) {
        for (int i = left; i <= right; i++) {
            if (inorder[i] == target) {
                return i;
            }
        }
        // 未命中
        return -1;
    }

    private void buildSubTree(TreeNode root, boolean leftSubTree,
                              int[] preorder,
                              int[] inorder, int left, int right) {
        int subRootIndex = search(inorder, left, right, preorder[preOrderIndex]);
        TreeNode subRoot = new TreeNode(preorder[preOrderIndex++]);
        if (leftSubTree) {
            root.left = subRoot;
        } else {
            root.right = subRoot;
        }
        // 构建左子树，至少有一个节点
        if (subRootIndex > left) {
            buildSubTree(subRoot, true, preorder, inorder, left, subRootIndex - 1);
        }
        // 构建右子树
        if (subRootIndex < right) {
            buildSubTree(subRoot, false, preorder, inorder, subRootIndex + 1, right);
        }
    }
}
