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
        ListNode head = new ListNode();
        ListNode tmp = head;
        for (int link : links) {
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val = link;
        }
        return head;
    }
}
