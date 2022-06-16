package com.janwarlen.recursion.first;

public class Complexity {
    public static void main(String[] args) {
        System.out.println(myPow(2.00000, -2));
    }

//    Maximum Depth of Binary Tree
    /**
     * Given a binary tree, find its maximum depth.
     *
     * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
     *
     * Note: A leaf is a node with no children.
     */
    /**
     * Given binary tree [3,9,20,null,null,15,7],
     * return its depth = 3.
     */
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * 个人答案：
     * 思路主要是定义出全局的最终结果
     * 然后遍历树，同步层级递增
     * 在叶子节点进行最大层树判断对比
     */
    public static int maxLevel = 1;

    public static int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        countLevel(root, 1);
        return maxLevel;
    }

    public static void countLevel(TreeNode node, int level) {
        if (null != node.left) {
            countLevel(node.left, level + 1);
        }
        if (null != node.right) {
            countLevel(node.right, level + 1);
        }
        if (level >= maxLevel) {
            maxLevel = level;
        }
    }

    /**
     * 尾递归方式，代码简洁，耗时更简短，额外内存消耗也少
     */
    public int maxDepthPro(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(maxDepthPro(root.left), maxDepthPro(root.right));
    }

//    Pow(x, n)
    /**
     * Implement pow(x, n), which calculates x raised to the power n (x^n).
     */
    /**
     * Example 1:
     *
     * Input: 2.00000, 10
     * Output: 1024.00000
     *
     * Example 2:
     *
     * Input: 2.10000, 3
     * Output: 9.26100
     *
     * Example 3:
     *
     * Input: 2.00000, -2
     * Output: 0.25000
     * Explanation: 2^-2 = 1/2^2 = 1/4 = 0.25
     */
    /**
     * Note:
     * <p>
     * -100.0 < x < 100.0
     * n is a 32-bit signed integer, within the range [−2^31, 2^31 − 1]
     */
    /**
     * 百度来的答案:
     * 思路主要是尾递归减少stack使用，避免stack overflow
     * 真正减少stack使用的是 'x * x'
     */
    public static double myPow(double x, int n) {
        if (x == 0d) {
            return 0;
        }
        if (n == 0) {
            return 1;
        }
        if (n < 0) {
            // core part，avoid negative used too much stack
            return ((1 / x) * myPow(1 / x, -(n + 1)));
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
