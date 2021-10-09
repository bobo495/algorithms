package pers.yibo.algorithms.leetcode.base;

/**
 * 数组转链表
 *
 * @author yibo
 * @date 2021-10-09 15:28
 **/
public class NodeUtils {
    public static ListNode arrToListNode(int[] links) {
        if (links.length == 0) {
            return null;
        }
        ListNode head = new ListNode(links[0]);
        ListNode tmp = head;
        for (int i = 1; i < links.length; i++) {
            tmp.next = new ListNode(links[i]);
            tmp = tmp.next;
        }
        return head;
    }

    public static ListNode[] matrixToListNodeArray(int[][] matrix) {
        if (matrix.length == 0) {
            return null;
        }
        ListNode[] listNodes = new ListNode[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            listNodes[i] = arrToListNode(matrix[i]);
        }
        return listNodes;
    }
}
