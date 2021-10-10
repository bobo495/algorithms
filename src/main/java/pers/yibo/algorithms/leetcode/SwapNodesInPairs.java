package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.ListNode;
import pers.yibo.algorithms.leetcode.base.NodeUtils;

/**
 * 24. 两两交换链表中的节点
 * <p>
 * https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * <p>
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 * <p>
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * @author aoyb
 * 2021/10/10 10:15
 */
public class SwapNodesInPairs {
    public ListNode swapPairs(ListNode head) {
        ListNode copy = head;
        boolean isFirst = true;

        ListNode lastTail = head;
        while (copy != null && copy.next != null) {
            ListNode second = copy.next;
            copy.next = copy.next.next;
            second.next = copy;
            if (isFirst) {
                head = second;
                isFirst = false;
            } else {
                lastTail.next = second;
            }
            lastTail = copy;

            copy = copy.next;
        }

        return head;
    }

    public static void main(String[] args) {
        ListNode test = NodeUtils.arrToListNode(new int[]{1, 2, 3, 4});

        SwapNodesInPairs s = new SwapNodesInPairs();

        System.out.println(s.swapPairs(test));
    }

}
