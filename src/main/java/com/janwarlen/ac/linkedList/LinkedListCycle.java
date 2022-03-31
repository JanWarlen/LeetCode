package com.janwarlen.ac.linkedList;

import java.util.HashMap;
import java.util.Map;

public class LinkedListCycle {

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
    
    public boolean hasCycle(ListNode head) {
        if (null == head || null == head.next) {
            return false;
        }
        Map<ListNode, Integer> count = new HashMap<>();
        int idx = 0;
        while (null != head.next) {
            if (count.containsKey(head)) {
                return true;
            } else {
                count.put(head, idx);
            }
            head = head.next;
        }
        return false;
    }

    /**
     * 核心思路
     * - 使用两个不同速度的游标
     * - 当两个游标碰撞到一起时，存在循环
     */
    public boolean hasCycleBest(ListNode head) {
        if(head == null || head.next == null) {
            return false;
        }
        ListNode slow = head;
        ListNode fast = head.next.next;
        while(fast  != null && slow!= null) {
            if(slow == fast) {
                return true;
            }
            slow = slow.next;
            fast = fast.next ==null?  null :  fast.next.next;
        }
        return false;

    }
}
