package com.janwarlen.recursion.second;

public class ValidateBinarySearchTree {

    public static boolean isValidBST(TreeNode root) {
        if (null == root) {
            return Boolean.TRUE;
        }
        // 利用LDR先序遍历，上一个遍历的值一定小于当前遍历的值特性进行递归判断
        // 上一个遍历值使用数组进行引用传递，便于递归中修改值
        return LDR(root, new Integer[1]);
    }

    public static boolean LDR(TreeNode root, Integer[] last) {
        boolean res = Boolean.TRUE;
        if (null != root.left) {
            // L
            res = res && LDR(root.left, last);
            if (null != last[0] && last[0] >= root.val) {
                return Boolean.FALSE;
            }
        }
        // D
        if (null != last[0] && last[0] >= root.val) {
            return Boolean.FALSE;
        }
        last[0] = root.val;
        // R
        if (null != root.right) {
            res = res && LDR(root.right, last);
        }
        return res;
    }

    public static void main(String[] args) {
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(1);
        first.left = second;
        System.out.println(isValidBST(first));
    }


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

    /**
     * 巧解
     * 利用左子树所有节点小于当前节点，右子树所有节点大于当前节点
     */
    class Solution {
        public boolean validate(TreeNode root, Integer low, Integer high) {
            // Empty trees are valid BSTs.
            if (root == null) {
                return true;
            }
            // The current node's value must be between low and high.
            if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
                return false;
            }
            // The left and right subtree must also be valid.
            return validate(root.right, root.val, high) && validate(root.left, low, root.val);
        }

        public boolean isValidBST(TreeNode root) {
            return validate(root, null, null);
        }
    }
}
