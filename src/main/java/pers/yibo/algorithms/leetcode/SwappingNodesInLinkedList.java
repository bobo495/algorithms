package pers.yibo.algorithms.leetcode;

import pers.yibo.algorithms.leetcode.base.ListNode;
import pers.yibo.algorithms.leetcode.base.NodeUtils;

import java.util.Arrays;

/**
 * 1721. 交换链表中的节点
 * <p>
 * 给你链表的头节点 head 和一个整数 k 。
 * <p>
 * 交换 链表正数第 k 个节点和倒数第 k 个节点的值后，返回链表的头节点（链表 从 1 开始索引）。
 *
 * @author aoyb
 * 2021/11/6 16:51
 */
public class SwappingNodesInLinkedList {

    /**
     * 值交换
     */
    public ListNode swapNodes(ListNode head, int k) {
        if (head.next == null) {
            return head;
        }
        // 用于遍历head
        ListNode tmp = head;
        ListNode l;

        // 遍历到第K个Node
        while (k-- > 1) {
            tmp = tmp.next;
        }
        l = tmp;

        ListNode r = head;
        tmp = tmp.next;
        while (tmp != null) {
            r = r.next;
            tmp = tmp.next;
        }
        // swap val
        k = l.val;
        l.val = r.val;
        r.val = k;
        return head;
    }


    /**
     * 指针交换
     */
    public ListNode swapNodes2(ListNode head, int k) {

        if (head.next == null) {
            return head;
        }

        // 用于遍历head
        ListNode tmp = head;
        // 第k-1个node 第k个Node 第k+1个Node 倒数第k+1个Node 倒数第k个Node 倒数第K-1个Node
        ListNode[] listNodes = new ListNode[6];
        Arrays.fill(listNodes, head);

        // 遍历到第N个Node
        int index = 1;

        while (tmp != null) {
            if (index == k - 1) {
                listNodes[0] = tmp;
            }
            if (index == k) {
                listNodes[1] = tmp;
            }
            if (index == k + 1) {
                listNodes[2] = tmp;
            }
            tmp = tmp.next;
            if (index > k - 1) {
                if (listNodes[5] != null) {
                    listNodes[5] = listNodes[5].next;
                }
                if (index > k) {
                    listNodes[4] = listNodes[4].next;
                    if (index > k + 1) {
                        listNodes[3] = listNodes[3].next;
                    }
                }
            }
            index++;
        }

        if (listNodes[1] == head) {
            if (listNodes[3] == listNodes[1]) {
                // 长度为2
                listNodes[4].next = listNodes[1];
                listNodes[1].next = null;
                return listNodes[4];
            }
            // k=1
            listNodes[1].next = null;
            listNodes[4].next = listNodes[2];
            listNodes[3].next = listNodes[1];
            return listNodes[4];
        }

        if (listNodes[4] == head) {
            if (listNodes[5] == listNodes[1]) {
                // 长度为2
                listNodes[4].next = null;
                listNodes[1].next = listNodes[4];
                return listNodes[1];
            }
            // k=length
            listNodes[4].next = null;
            listNodes[1].next = listNodes[5];
            listNodes[0].next = listNodes[4];
            return listNodes[1];
        }

        for (ListNode l : listNodes) {
            System.out.println(l.val);
        }

        // 重叠2个
        if (listNodes[1] == listNodes[3] && listNodes[2] == listNodes[4]) {
            listNodes[0].next = listNodes[2];
            listNodes[2].next = listNodes[1];
            listNodes[1].next = listNodes[5];
            return head;
        }
        if (listNodes[0] == listNodes[4] && listNodes[1] == listNodes[5]) {
            listNodes[3].next = listNodes[1];
            listNodes[1].next = listNodes[0];
            listNodes[0].next = listNodes[2];
            return head;
        }

        listNodes[0].next = listNodes[4];
        listNodes[1].next = listNodes[5];
        listNodes[3].next = listNodes[1];
        listNodes[4].next = listNodes[2];


        return head;
    }


    public static void main(String[] args) {
        SwappingNodesInLinkedList s = new SwappingNodesInLinkedList();
        ListNode head = NodeUtils.arrToListNode(new int[]{1, 2, 3, 4, 5});
        System.out.println(s.swapNodes(head, 2));
    }
}
