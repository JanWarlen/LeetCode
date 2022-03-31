package com.janwarlen.recursion.second;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Given the root of a binary tree, return the inorder traversal of its nodes' values.
 * <p>
 * Example 1:
 * Input: root = [1,null,2,3]
 * Output: [1,3,2]
 * <p>
 * Example 2:
 * <p>
 * Input: root = []
 * Output: []
 * <p>
 * Example 3:
 * <p>
 * Input: root = [1]
 * Output: [1]
 * <p>
 * Example 4:
 * <p>
 * Input: root = [1,2]
 * Output: [2,1]
 * <p>
 * Example 5:
 * <p>
 * Input: root = [1,null,2]
 * Output: [1,2]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * The number of nodes in the tree is in the range [0, 100].
 * -100 <= Node.val <= 100
 */
public class BinaryTreeInorderTraversal {

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

    public static List<Integer> inorderTraversal_Recursion(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        solution(root, res);
        return res;
    }

    private static void solution(TreeNode root, List<Integer> res) {
        if (null == root) {
            return;
        }
        if (null != root.left) {
            solution(root.left, res);
        }
        res.add(root.val);
        if (null != root.right) {
            solution(root.right, res);
        }
    }

    /**
     * 循环解法
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<Integer> res = new ArrayList<>();
        ArrayDeque<TreeNode> dp = new ArrayDeque<>();
        TreeNode index = root;
        while (!dp.isEmpty() || null != index) {
            if (null != index) {
                dp.push(index);
                index = index.left;
            } else {
                TreeNode pop = dp.pop();
                dp.poll();
                res.add(pop.val);
                index = pop.right;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode rootRight = new TreeNode(2);
        TreeNode rootRightLeft = new TreeNode(3);
        root.right = rootRight;
        rootRight.left = rootRightLeft;
        inorderTraversal(root).stream().forEach(item -> {
            System.out.print(item + ",");
        });
    }

}
