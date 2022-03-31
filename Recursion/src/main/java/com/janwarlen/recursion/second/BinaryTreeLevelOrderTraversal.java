package com.janwarlen.recursion.second;

import java.util.*;

/**
 * Given the root of a binary tree, return the level order traversal of its nodes' values. (i.e., from left to right, level by level).
 * <p>
 * Example 1:
 * <p>
 * Input: root = [3,9,20,null,null,15,7]
 * Output: [[3],[9,20],[15,7]]
 * <p>
 * Example 2:
 * <p>
 * Input: root = [1]
 * Output: [[1]]
 * <p>
 * Example 3:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 2000].
 * -1000 <= Node.val <= 1000
 */
public class BinaryTreeLevelOrderTraversal {

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static List<List<Integer>> levelOrderRecursion(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        solution(root, res, 0);
        return res;
    }

    private static void solution(TreeNode root, List<List<Integer>> res, int level) {
        if (null == root) {
            return;
        }
        if (level >= res.size()) {
            List<Integer> ints = new ArrayList<>();
            res.add(level, ints);
        }
        List<Integer> ints = res.get(level);
        ints.add(root.val);
        solution(root.left, res, level + 1);
        solution(root.right, res, level + 1);
    }

    /**
     * 循环解法
     */
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (null == root) {
            return res;
        }
        Queue<TreeNode> dp = new LinkedList<>();
        dp.add(root);
        while (!dp.isEmpty()) {
            List<Integer> ints = new ArrayList<>();
            int size = dp.size();
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = dp.poll();
                ints.add(treeNode.val);
                if (null != treeNode.left) {
                    dp.add(treeNode.left);
                }
                if (null != treeNode.right) {
                    dp.add(treeNode.right);
                }
            }
            res.add(ints);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootRight = new TreeNode(2);
        TreeNode rootRightLeft = new TreeNode(3);
        root.right = rootRight;
        rootRight.left = rootRightLeft;
        levelOrder(root).stream().forEach(item -> {
            item.stream().forEach(ele -> System.out.print(ele + ","));
            System.out.println("");
        });
    }

}
