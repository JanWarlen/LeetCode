package com.janwarlen.recursion.first;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Conclusion {

    public static void main(String[] args) {
        System.out.println(JSONObject.toJSONString(generateTrees(1)));
    }

//    Merge Two Sorted Lists
    /**
     * Merge two sorted linked lists and return it as a new list.
     * The new list should be made by splicing together the nodes of the first two lists.
     */
    /**
     * Example:
     * <p>
     * Input: 1->2->4, 1->3->4
     * Output: 1->1->2->3->4->4
     */

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static void mergeTwoLists() {
        /*ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(2);
        l1.next = l2;
        ListNode l3 = new ListNode(4);
        l2.next = l3;
        ListNode r1 = new ListNode(1);
        ListNode r2 = new ListNode(3);
        r1.next = r2;
        ListNode r3 = new ListNode(4);
        r2.next = r3;
        mergeTwoLists(l1, r1);*/
        ListNode l1 = new ListNode(2);
        ListNode r1 = new ListNode(1);
        mergeTwoList(l1, r1);
    }

    public static ListNode mergeTwoList(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        } else if (l1 == null) {
            return l2;
        } else if (l2 == null) {
            return l1;
        }
        if (null != l1.next && l2.val > l1.next.val) {
            mergeTwoList(l1.next, l2);
        } else {
            // l1.next为空 或者 l2.val <= l1.next.val
            // 插入l1后,l1.next前
            ListNode tmp = l2;
            l2 = l2.next;
            tmp.next = l1.next;
            l1.next = tmp;
            mergeTwoList(l1.next, l2);
        }
        return l1;
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode tmp = new ListNode(0);
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

    public ListNode mergeTwoListsPro(ListNode l1, ListNode l2){
        if(l1 == null) return l2;
        if(l2 == null) return l1;
        if(l1.val < l2.val){
            l1.next = mergeTwoListsPro(l1.next, l2);
            return l1;
        } else{
            l2.next = mergeTwoListsPro(l1, l2.next);
            return l2;
        }
    }

    // K-th Symbol in Grammar

//    On the first row, we write a 0.
//    Now in every subsequent row, we look at the previous row and replace each occurrence of 0 with 01,
//    and each occurrence of 1 with 10.
//
//    Given row N and index K, return the K-th indexed symbol in row N. (The values of K are 1-indexed.) (1 indexed).
//    Examples:
//    Input: N = 1, K = 1
//    Output: 0
//
//    Input: N = 2, K = 1
//    Output: 0
//
//    Input: N = 2, K = 2
//    Output: 1
//
//    Input: N = 4, K = 5
//    Output: 1
//
//    Explanation:
//    row 1: 0
//    row 2: 01
//    row 3: 0110
//    row 4: 01101001

//    N will be an integer in the range [1, 30].
//    K will be an integer in the range [1, 2^(N-1)].

    public static int kthGrammar(int N, int K) {
        if (K == 1) {
            return 0;
        }
        return kthGrammar(N, K - (1 << (int) Math.ceil(Math.log(K) / Math.log(2) - 1))) == 0 ? 1 : 0;
    }

    public int kthGrammarPro(int n, int k) {
        return Integer.bitCount(k-1) & 1;
    }

    // Unique Binary Search Trees II
    // Given an integer n,
    // generate all structurally unique BST's (binary search trees) that store values 1 ... n.
    // Example:
//    Input: 3
//    Output:
//            [
//            [1,null,3,2],
//            [3,2,null,1],
//            [3,1,null,null,2],
//            [2,1,3],
//            [1,null,2,null,3]
//            ]
//    Explanation:
//    The above output corresponds to the 5 unique BST's shown below:
//
//            1         3     3      2      1
//            \       /     /      / \      \
//            3     2     1      1   3      2
//            /     /       \                 \
//            2     1         2                 3
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }

        public int getVal() {
            return val;
        }

        public TreeNode getLeft() {
            return left;
        }

        public TreeNode getRight() {
            return right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    public static List<TreeNode> generateTrees(int n) {
        if(n == 0) {
            return  new ArrayList<>();
        }
        return generateTrees(1, n);
    }
    public static List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> list = new ArrayList<>();
        if(start > end) {
            list.add(null);
            return list;
        }
        for(int i = start; i <= end; i++) {
            //1, 2, 3, 4, 5
            List<TreeNode> leftSubTree = generateTrees(start, i-1);
            List<TreeNode> rightSubTree = generateTrees(i + 1, end);

            for (TreeNode left : leftSubTree) {
                for (TreeNode right : rightSubTree) {
                    TreeNode now = new TreeNode(i);
                    now.left = left;
                    now.right = right;
                    list.add(now);
                }
            }
        }
        return list;
    }


    /**
     * 用了动态规划
     */
    public static List<TreeNode> generateTreesPro(int n) {
        List<TreeNode>[] result = new List[n + 1];
        result[0] = new ArrayList<TreeNode>();
        if (n == 0) {
            return result[0];
        }

        result[0].add(null);
        for (int len = 1; len <= n; len++) {
            result[len] = new ArrayList<TreeNode>();
            for (int j = 0; j < len; j++) {
                for (TreeNode nodeL : result[j]) {
                    for (TreeNode nodeR : result[len - j - 1]) {
                        TreeNode node = new TreeNode(j + 1);
                        node.left = nodeL;
                        node.right = clone(nodeR, j + 1);
                        result[len].add(node);
                    }
                }
            }
        }
        return result[n];
    }

    private static TreeNode clone(TreeNode n, int offset) {
        if (n == null) {
            return null;
        }
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }

}
