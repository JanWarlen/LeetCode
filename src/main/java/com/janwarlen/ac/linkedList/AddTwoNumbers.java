package com.janwarlen.ac.linkedList;

public class AddTwoNumbers {
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode res = new ListNode();
        ListNode lr = res;
        int add = 0;
        while (null != l1 || null != l2) {
            int x = null != l1 ? l1.val : 0;
            int y = null != l2 ? l2.val : 0;
            int i = x + y + add;
            add = i / 10;
            ListNode tmp = new ListNode(i % 10);
            lr.next = tmp;
            lr = tmp;
            if (null != l1) {
                l1 = l1.next;
            }
            if (null != l2) {
                l2 = l2.next;
            }
        }
        if (add > 0) {
            ListNode tmp = new ListNode(add);
            lr.next = tmp;
        }
        return res.next;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(9);
        ListNode l2 = new ListNode(9);
        l1.next = l2;
        ListNode l3 = new ListNode(9);
        l2.next = l3;
        ListNode l4 = new ListNode(9);
        l3.next = l4;
        ListNode r1 = new ListNode(1);
        System.out.println(addTwoNumbers(l1, r1));
    }
}
