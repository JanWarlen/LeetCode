package com.janwarlen.ac.linkedList;

import java.util.concurrent.atomic.AtomicBoolean;

public class PalindromeLinkedList {
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

    public static boolean isPalindrome(ListNode head) {
        ListNode end = head;
        AtomicBoolean res = new AtomicBoolean(true);
        AtomicBoolean stop = new AtomicBoolean(false);
        solution(head, end, res, stop);
        return res.get();
    }

    private static ListNode solution(ListNode head, ListNode end, AtomicBoolean res, AtomicBoolean stop) {
        if (null == end) {
            return head;
        }
        head = solution(head, end.next, res, stop);
        if (head == end || end.next == head) {
            stop.set(true);
            return head;
        }
        if (head.val != end.val && !stop.get()) {
            res.set(false);
        }
        return head.next;
    }

    /**
     * 思路是将链表切分两半，然后同步对比
     * 切半的思路
     * - 快游标每次移动2格，慢游标每次移动1格
     * - 前一半利用一个独立游标，进行reverse重新组装
     * - 当快游标移动至末尾(null || 链表长度为奇数时的最后一个元素)时，慢游标正好在中间位置
     */
    public static boolean isPalindromeBest(ListNode head) {
        //1,2,3,1 slow points to 3, fast points to null
        //1,2,3 slow points to 2, fast points to 3
        ListNode fast = head;
        ListNode slow = head;
        ListNode prev = null;
        while(fast != null && fast.next != null) {
            fast = fast.next.next;
            ListNode next = slow.next;
            slow.next = prev;
            prev = slow;
            slow = next;
        }
        ListNode right = fast == null ? slow : slow.next;
        ListNode left = prev;
        while(left != null && right != null && left.val == right.val) {
            left = left.next;
            right = right.next;
        }
        return left == null && right == null;
    }

    public static void main(String[] args) {
        ListNode listNode = new ListNode(1);
        ListNode listNode2 = new ListNode(2);
        listNode.next = listNode2;
        ListNode listNode3 = new ListNode(3);
        listNode2.next = listNode3;
        ListNode listNode4 = new ListNode(3);
        listNode3.next = listNode4;
        ListNode listNode5 = new ListNode(2);
        listNode4.next = listNode5;
        ListNode listNode6 = new ListNode(1);
        listNode5.next = listNode6;
        System.out.println(isPalindromeBest(listNode));
    }
}
