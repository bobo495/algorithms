package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.ListNode;
import pers.yibo.algorithms.leetcode.base.NodeUtils;

/**
 * 25. K 个一组翻转链表
 * <p>
 * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
 * <p>
 * 给你一个链表，每k个节点一组进行翻转，请你返回翻转后的链表。
 * <p>
 * k是一个正整数，它的值小于或等于链表的长度。
 * <p>
 * 如果节点总数不是k的整数倍，那么请将最后剩余的节点保持原有顺序。
 * <p>
 * 进阶：
 * <p>
 * 你可以设计一个只使用常数额外空间的算法来解决此问题吗？
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 * <p>
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 * <p>
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 * <p>
 * 输入：head = [1,2,3,4,5], k = 1
 * 输出：[1,2,3,4,5]
 *
 * @author aoyb
 * 2021/10/10 10:42
 */
public class ReverseNodesInKGroup {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (k == 1) {
            return head;
        }
        ListNode srcHead = head;
        ListNode result = null;
        ListNode resultTail = null;
        ListNode sub = new ListNode(head.val);
        ListNode subTail = sub;
        ListNode srcTail = null;
        // 反转子链表
        int subLength = 1;
        while (head.next != null) {
            ListNode tmp = new ListNode(head.next.val);
            tmp.next = sub;
            sub = tmp;
            head = head.next;
            subLength++;
            if (subLength == k) {
                subLength = 1;
                head = head.next;
                srcTail = head;
                if (result == null) {
                    result = sub;
                    if (head == null) {
                        return result;
                    }
                    sub = new ListNode(head.val);
                    resultTail = subTail;
                    subTail = sub;
                } else {
                    resultTail.next = sub;
                    resultTail = subTail;
                    if (head == null) {
                        return result;
                    }
                    sub = new ListNode(head.val);
                    subTail = sub;
                }
            }
        }
        if (result == null) {
            return srcHead;
        }

        if (subLength != k && resultTail != null) {
            resultTail.next = srcTail;
        }
        return result;
    }

    public static void main(String[] args) {
        ListNode test = NodeUtils.arrToListNode(new int[]{1, 2, 3, 4, 5, 6, 7, 8});
        int size = 10;
        ReverseNodesInKGroup r = new ReverseNodesInKGroup();

        System.out.println(r.reverseKGroup(test, size));
    }
}
