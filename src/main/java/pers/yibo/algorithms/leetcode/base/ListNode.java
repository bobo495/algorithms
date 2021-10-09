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


    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public String toString() {
        ListNode tmp = this;
        StringBuilder out = new StringBuilder();
        out.append("[");
        while (tmp.next != null) {
            out.append(tmp.val).append(",");
            tmp = tmp.next;
        }
        return out.append(tmp.val).append("]").toString();
    }
}