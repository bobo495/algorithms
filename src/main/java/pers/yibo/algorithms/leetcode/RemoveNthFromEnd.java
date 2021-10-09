package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.ListNode;

import java.util.Objects;

/**
 * 19. 删除链表的倒数第 N 个结点
 *
 * @author yibo
 * @date 2021-10-09 10:20
 **/
public class RemoveNthFromEnd {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }
        ListNode right = head;
        int i = 0;
        ListNode left = head;
        while (right.next != null) {
            i++;
            if (i > n) {
                left = left.next;
            }
            right = right.next;
        }
        if (i < n) {
            return head.next;
        }
        left.next = left.next.next;

        return head;
    }

    public static void main(String[] args) {
        // 不能构建空链表
        int[] links = new int[]{1, 2};
        int n = 2;
        ListNode head = new ListNode(links);
        RemoveNthFromEnd r = new RemoveNthFromEnd();
        System.out.println(r.removeNthFromEnd(head, n));
    }
}
