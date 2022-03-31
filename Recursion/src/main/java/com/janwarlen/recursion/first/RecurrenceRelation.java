package com.janwarlen.recursion.first;

import java.util.ArrayList;
import java.util.List;

public class RecurrenceRelation {
    public static void main(String[] args) {
        PascalsTriangleII();
    }

    //Reverse Linked List
    /**
     * Input: 1->2->3->4->5->NULL
     * Output: 5->4->3->2->1->NULL
     */
    public static void reverseLinkedList() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next = new ListNode(7);
        head.next.next.next.next.next.next.next = new ListNode(8);
        ListNode tmp = head;
        ListNode listNode = reverseList(head);
    }

     public static class ListNode {
         int val;
         ListNode next;
         ListNode(int x) { val = x; }
     }

    /**
     * 个人答案
     * 思路是替换节点的值，使链表reverse
     */
    public static ListNode localReverseList(ListNode head) {
        if (null == head) {
            return head;
        }
        ListNode tail = head;
        ListNode res = head;
        reverse(head, tail);
        return res;
    }

    /**
     * 官方遍历答案
     * 思路主要是将节点之间的next指向链接修改调整，最终reverse
     */
    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode p = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return p;
    }

    public static int reverse(ListNode head, ListNode tail) {
        if (null != tail.next) {
            int reverse = reverse(head, tail.next);
            for (int i = 0; i < reverse; i++) {
                head = head.next;
            }
            if (head == tail || tail.next == head || reverse == 0) {
                return 0;
            }
            int tmpVal = tail.val;
            tail.val = head.val;
            head.val = tmpVal;
            return reverse + 1;
        } else {
            int tmp = head.val;
            head.val = tail.val;
            tail.val = tmp;
            return 1;
        }
    }

    // Search in a Binary Search Tree
    /**
     * [start] Search in a Binary Search Tree
     * Given the root node of a binary search tree (BST) and a value.
     * You need to find the node in the BST that the node's value equals the given value.
     * Return the subtree rooted with that node.
     * If such node doesn't exist, you should return NULL.
     */
    public static void searchBST() {
        // init data
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right = new TreeNode(7);
        searchBST(root, 3);
    }

     public static class TreeNode {
         int val;
         TreeNode left;
         TreeNode right;
         TreeNode(int x) { val = x; }
     }

    public static TreeNode searchBST(TreeNode root, int val) {
        if (null == root || val == root.val) {
            return root;
        }
        // compare with root.val
        if (val > root.val) {
            return searchBST(root.right, val);
        } else {
            return searchBST(root.left, val);
        }
    }


//  Pascal's Triangle II

    /**
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * Note that the row index starts from 0.
     * In Pascal's triangle, each number is the sum of the two numbers directly above it.
     *
     * Input: 3
     * Output: [1,3,3,1]
     */
    public static void PascalsTriangleII() {
        List<Integer> row = getRow(4);
        System.out.println(row);
    }

    /**
     * 个人答案
     * 思路：前两层直接返回，后续层级由上一层级计算得出
     */
    public static List<Integer> getRow(int rowIndex) {
        List<Integer> res = new ArrayList<>();
        if (rowIndex < 2) {
            for (int i = 0; i <= rowIndex; i++) {
                res.add(1);
            }
        } else {
            List<Integer> preLevel = getRow(rowIndex - 1);
            res.add(1);
            for (int i = 1; i < preLevel.size();i++) {
                res.add(preLevel.get(i-1) + preLevel.get(i));
            }
            res.add(1);
        }
        return res;
    }
}
