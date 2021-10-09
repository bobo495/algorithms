package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.ListNode;
import pers.yibo.algorithms.leetcode.base.NodeUtils;

/**
 * 23. 合并K个升序链表
 * <p>
 * https://leetcode-cn.com/problems/merge-k-sorted-lists
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * <p>
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * <p>
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 * 1->4->5,
 * 1->3->4,
 * 2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 * 示例 2：
 * <p>
 * 输入：lists = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：lists = [[]]
 * 输出：[]
 *
 * @author yibo
 * @date 2021-10-09 15:05
 **/
public class MergeKLists {
    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        ListNode result = null;
        for (ListNode list : lists) {
            result = mergeTwoList(result, list);
        }

        return result;
    }

    /**
     * b 合并至 a
     *
     * @param a 单向有序链表
     * @param b 单向有序链表
     */
    public ListNode mergeTwoList(ListNode a, ListNode b) {

        if (a == null) {
            return b;
        }

        ListNode head = a;

        /*
        a: 1->4->5
        b: 2->3->6
        最终a: 1->2->3->4->5->6
        修改指针的场景：
        1. a.val<=b.val && a.next.val > b.val => a.next=b
        2. a.val> b.val && a.val <= b.next.val => b.next=a
         */
        while (a.next != null && b.next != null) {
            if (a.val <= b.val) {
                if (a.next.val > b.val) {
                    ListNode tmpA = a;
                    a = a.next;
                    tmpA.next = b;
                } else {
                    a = a.next;
                }
            } else {
                if (a.val <= b.next.val) {
                    ListNode tmpB = b;
                    b = b.next;
                    tmpB.next = a;
                } else {
                    b = b.next;
                }
            }
        }


        // todo 单向拼接处理
        if (a.next == null && b.next != null) {
            return addTail(head, b, a);
        } else {
            return addTail(head, a, b);
        }
    }

    public ListNode addTail(ListNode head, ListNode l1, ListNode l2) {

        ListNode tmpL1 = l1;
        while (l1.next != null) {
            if (l1.val > l2.val) {
                tmpL1.next = l2;
                l2.next = l1;
                return head;
            }
            l1 = l1.next;
        }

        if (l1.val > l2.val) {
            if (tmpL1.next != null) {
                tmpL1.next = l2;
                l2.next = l1;
            } else {
                l2.next = l1;
                return l2;
            }
        } else {
            l1.next = l2;
        }
        return head;

    }

    public static void main(String[] args) {

//        int[][] lists = new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}};
//        int[][] lists = new int[][]{{1}, {0}};
        int[][] lists = new int[][]{{1, 2, 3}, {4, 5, 6, 7}};

        ListNode[] listNodes = NodeUtils.matrixToListNodeArray(lists);
        MergeKLists m = new MergeKLists();
        System.out.println(m.mergeKLists(listNodes));

//        ListNode a = NodeUtils.arrToListNode(new int[]{1, 4, 5, 7, 10, 11});
//        ListNode b = NodeUtils.arrToListNode(new int[]{2, 3, 6, 8, 9});
//        System.out.println(m.mergeTwoList(a, b));
    }
}
