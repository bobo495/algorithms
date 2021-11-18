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
        if (node.left == null && node.right != null) {
            node.val = node.val + node.right.val;
            tilt += Math.abs(node.right.val);
        } else if (node.right == null && node.left != null) {
            node.val = node.val + node.left.val;
            tilt += Math.abs(node.left.val);
        } else if (node.right != null) {
            node.val = node.val + node.left.val + node.right.val;
            tilt += Math.abs(node.left.val - node.right.val);
        }
        return tilt;
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
