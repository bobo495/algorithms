package pers.yibo.algorithms.leetcode.base;

/**
 * 单向链表
 *
 * 注意：不能初始化空链表
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
        this.val = links[0];
        ListNode tmp = this;
        for (int i = 1; i < links.length; i++) {
            tmp.next = new ListNode();
            tmp = tmp.next;
            tmp.val = links[i];
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
            return "[" + tmp.val + "]";
        }
        StringBuilder out = new StringBuilder();
        out.append("[");
        while (tmp.next != null) {
            out.append(tmp.val).append(",");
            tmp = tmp.next;
        }
        return out.append(tmp.val).append("]").toString();
    }
}