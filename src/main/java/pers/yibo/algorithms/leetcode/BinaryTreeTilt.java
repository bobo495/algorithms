package pers.yibo.algorithms.leetcode;

/**
 * 563. 二叉树的坡度
 *
 * @author yibo
 * @date 2021-11-18 09:04
 **/
public class BinaryTreeTilt {
    public int findTilt(TreeNode root) {
        return sizeCount(root, 0);
    }

    public int sizeCount(TreeNode node, int tilt) {
        if (node == null) {
            return 0;
        }
        tilt += sizeCount(node.left, tilt) + sizeCount(node.right, tilt);
        node.val = getVal(node) + getVal(node.left) + getVal(node.right);
        tilt += Math.abs(getVal(node.left) - getVal(node.right));
        return tilt;
    }

    public int getVal(TreeNode node) {
        return node == null ? 0 : node.val;
    }

    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
