package com.janwarlen.recursion.first;

public class PrincipleOfRecursion {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {

    }
//    Reverse String

    /**
     * Input: ["h","e","l","l","o"]
     * Output: ["o","l","l","e","h"]
     */
    public static void reverseString() {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        printReverse(s);
    }

    private static void printReverse(char[] str) {
        helper(0, str);
    }

    private static void helper(int index, char[] str) {
        if (str == null || index >= str.length / 2) {
            return;
        }
        helper(index + 1, str);
        char tmp = str[str.length - 1 - index];
        str[str.length - 1 - index] = str[index];
        str[index] = tmp;
        System.out.print(str[index]);
    }

//  Swap Nodes in Pairs

    /**
     * Given 1->2->3->4, you should return the list as 2->1->4->3.
     */
    public static void swapNodesInPairs() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        swapPairs(head);
    }

    public static ListNode swapPairs(ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        int tmp = head.val;
        head.val = head.next.val;
        head.next.val = tmp;
        swapPairs(head.next.next);
        return head;
    }

}
