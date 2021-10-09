package pers.yibo.algorithms.leetcode.base;

/**
 * 单向链表
 *
 * @author yibo
 * @date 2021-10-09 10:19
 **/
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int val) {
        this.val = val;
    }

    public ListNode(int[] links) {
        if (links.length == 0) {
            return;
        }
        ListNode tmp = this;
        for (int link : links) {
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val = link;
        }
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode tmp = this;
        if (tmp.next == null) {
            return "[" + "]";
        }
        StringBuilder out = new StringBuilder();
        out.append("[");
        while (tmp.next != null) {
            tmp = tmp.next;
            out.append(tmp.val).append(",");
        }
        return out.deleteCharAt(out.length() - 1).append("]").toString();
    }
}