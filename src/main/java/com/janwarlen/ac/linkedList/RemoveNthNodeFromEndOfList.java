package com.janwarlen.ac.linkedList;

public class RemoveNthNodeFromEndOfList {
    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int length = 1;
        ListNode p = head;
        while (null != p.next) {
            p = p.next;
            length++;
        }
        p = head;
        ListNode pre = p;
        for (int i = 0; i < length - n; i++) {
            pre = p;
            p = p.next;
        }
        if (null != p.next) {
            p.val = p.next.val;
            p.next = p.next.next;
        } else if (p != head) {
            pre.next = null;
        } else {
            head = null;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        removeNthFromEnd(listNode, 3);
    }
}
