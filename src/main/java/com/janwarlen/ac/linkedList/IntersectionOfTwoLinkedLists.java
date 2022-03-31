package com.janwarlen.ac.linkedList;

import java.util.HashMap;

public class IntersectionOfTwoLinkedLists {

    /**
     * 鬼才思路
     * 第一次遍历时，短的先到头，然后指针指向长链表头节点，继续同时遍历
     * 然后遍历长链表的到头时，再指向短链表头节点，此时，遍历指针刚好处于平行位置
     * 此时遍历定会同时访问相交节点
     */
    public static ListNode getIntersectionNodeProPlus(ListNode headA, ListNode headB) {
        //boundary check
        if(headA == null || headB == null) return null;

        ListNode a = headA;
        ListNode b = headB;

        //if a & b have different len, then we will stop the loop after second iteration
        while( a != b){
            //for the end of first iteration, we just reset the pointer to the head of another linkedlist
            a = a == null? headB : a.next;
            b = b == null? headA : b.next;
        }

        return a;

    }

    public static ListNode getIntersectionNodePro(ListNode headA, ListNode headB) {
        int headALen = 0, headBLen = 0;
        ListNode lenA = headA;
        while (null != lenA) {
            headALen++;
            lenA = lenA.next;
        }
        ListNode lenB = headB;
        while (null != lenB) {
            headBLen++;
            lenB = lenB.next;
        }
        if (headALen > headBLen) {
            int diff = headALen - headBLen;
            for (int i = 0; i < diff; i++) {
                headA = headA.next;
            }
        } else if (headBLen > headALen) {
            int diff = headBLen - headALen;
            for (int i = 0; i < diff; i++) {
                headB = headB.next;
            }
        }
        while (null != headA && null != headB && headA != headB) {
            headA = headA.next;
            headB = headB.next;
        }
        return (null != headA && null != headB) ? headA : null;
    }

    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        HashMap<ListNode, Object> count = new HashMap<>();
        while (null != headA) {
            count.put(headA, 1);
            headA = headA.next;
        }
        while (null != headB) {
            if (null != count.get(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode first = new ListNode(1);
        ListNode second = new ListNode(2);
        first.next = second;
        ListNode third = new ListNode(3);
        second.next = third;
        ListNode fourth = new ListNode(4);
        third.next = fourth;
        ListNode fifth = new ListNode(5);
        fourth.next = fifth;

        ListNode headB = new ListNode(1);
        ListNode headB_2 = new ListNode(2);
        headB.next = headB_2;
        ListNode headB_3 = new ListNode(3);
        headB_2.next = headB_3;
        ListNode headB_4 = new ListNode(4);
        headB_3.next = headB_4;
        ListNode headB_5 = new ListNode(5);
        headB_4.next = headB_5;
        headB_5.next = first;
        getIntersectionNodeProPlus(first, headB);
    }
}
