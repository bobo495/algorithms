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
            System.out.println(result);
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

        if (b == null) {
            return a;
        }

        ListNode headA = a;
        ListNode headB = b;
        /*
        a: 1->4->5
        b: -1->0->2->3->6
        最终a: 0->1->2->3->4->5->6
        修改指针的场景：
        1. a.val<=b.val && a.next.val > b.val => a.next=b
        2. a.val> b.val && a.val <= b.next.val => b.next=a
        3. a.val>b.val &&
         */
        ListNode lastA = a;
        boolean halfChanged = false;
        while (a != null && b != null) {
            if (a.val <= b.val) {
                if (a.next != null) {
                    if (a.next.val > b.val) {
                        ListNode tmpA = a;
                        lastA = a;
                        a = a.next;
                        tmpA.next = b;
                        halfChanged = true;
                    } else {
                        lastA = a;
                        a = a.next;
                    }
                } else {
                    a.next = b;
                    return headA;
                }
            } else {
                if (b.next != null) {
                    if (a.val <= b.next.val) {
                        if (headA == a) {
                            headA = headB;
                        }
                        ListNode tmpB = b;
                        b = b.next;
                        tmpB.next = a;
                    } else {
                        b = b.next;
                    }
                } else {
                    if (headA != lastA) {
                        if (!halfChanged) {
                            lastA.next = b;
                        }
                        b.next = a;
                        break;
                    } else {
                        b.next = a;
                        if (!halfChanged) {
                            return headB;
                        } else {
                            return headA;
                        }
                    }
                }
            }
        }

        return headA;
    }

    public static void main(String[] args) {

//        int[][] lists = new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}};
//        int[][] lists = new int[][]{{1}, {0}};
        int[][] lists = new int[][]{{-10, -6, -5}, {-9}};
//        int[][] lists = new int[][]{{-10, -4, -3, -2, 1}, {-7, -7}};
        ListNode[] listNodes = NodeUtils.matrixToListNodeArray(lists);
        MergeKLists m = new MergeKLists();
        System.out.println(m.mergeKLists(listNodes));

//        ListNode a = NodeUtils.arrToListNode(new int[]{1, 4, 5, 7, 10, 11});
//        ListNode b = NodeUtils.arrToListNode(new int[]{2, 3, 6, 8, 9});
//        System.out.println(m.mergeTwoList(a, b));
    }
}
