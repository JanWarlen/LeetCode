package com.janwarlen.ac.linkedList;

import java.util.concurrent.atomic.AtomicBoolean;

public class ReverseLinkedList {

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

    public static ListNode reverseList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode start = head;
        ListNode tmp = head;
        solution(start, tmp, new AtomicBoolean(false));
        return head;
    }

    private static ListNode solution(ListNode start, ListNode end, AtomicBoolean stop) {
        if (null == end) {
            return start;
        }
        start = solution(start, end.next, stop);
        if (!stop.get()) {
            int tmp = start.val;
            start.val = end.val;
            end.val = tmp;
        }
        if (start == end || start.next == end) {
            stop.set(true);
            return start;
        }
        return start.next;
    }

    public static ListNode reverseListPro(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode res = null;
        while (null != head) {
            ListNode next = head.next;
            head.next = res;
            res = head;
            head = next;
        }
        return res;
    }

    public static ListNode reverseListPro2(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode res = null;
        return solver(res, head);
    }

    private static ListNode solver(ListNode res, ListNode head) {
        if (null != head) {
            ListNode next = head.next;
            head.next = res;
            res = head;
            head = next;
            return solver(res, head);
        }
        return res;
    }


    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(4);
        listNode3.next = listNode4;
        ListNode listNode5 = new ListNode(5);
        listNode4.next = listNode5;
        reverseListPro2(listNode);
    }

}
