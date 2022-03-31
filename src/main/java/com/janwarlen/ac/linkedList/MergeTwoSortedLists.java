package com.janwarlen.ac.linkedList;

public class MergeTwoSortedLists {

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

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (null == list1 || null == list2) {
            return null == list1 ? list2 : list1;
        }
        ListNode res = list1;
        while (null != list1 && null != list2) {
            if (list1.val >= list2.val) {
                int val = list1.val;
                list1.val = list2.val;
                list2.val = val;
                ListNode next = list1.next;
                list1.next = list2;
                list2 = list2.next;
                list1.next.next = next;
            } else if (null != list1.next) {
                list1 = list1.next;
            } else {
                list1.next = list2;
                return res;
            }
        }
        return res;
    }

    /**
     * 简化代码，提升可阅读性
     */
    public static ListNode mergeTwoListsPro(ListNode list1, ListNode list2) {
        ListNode tmp = new ListNode();
        ListNode res = tmp;
        while (null != list1 && null != list2) {
            if (list1.val <= list2.val) {
                res.next = list1;
                list1 = list1.next;
            } else {
                res.next = list2;
                list2 = list2.next;
            }
            res = res.next;
        }
        if (null != list1) {
            res.next = list1;
        }
        if (null != list2) {
            res.next = list2;
        }
        return tmp.next;
    }

    public static void main(String[] args) {
        /*ListNode a1 = new ListNode(2);
        ListNode a2 = new ListNode(2);
        a1.next = a2;
        ListNode a3 = new ListNode(3);
        a2.next = a3;

        ListNode b1 = new ListNode(1);
        ListNode b2 = new ListNode(2);
        b1.next = b2;
        ListNode b3 = new ListNode(4);
        b2.next = b3;*/

        /*ListNode a1 = new ListNode(2);
        ListNode b1 = new ListNode(1);*/

        ListNode a1 = new ListNode(-9);
        ListNode a2 = new ListNode(3);
        a1.next = a2;

        ListNode b1 = new ListNode(5);
        ListNode b2 = new ListNode(7);
        b1.next = b2;

        mergeTwoLists(a1,b1);
    }
}
