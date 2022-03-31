package com.janwarlen.ac.trees;

import java.util.concurrent.atomic.AtomicInteger;

public class MaximumDepthOfBinaryTree {

    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        AtomicInteger max = new AtomicInteger(1);
        solution(root, 1, max);
        return max.get();
    }

    private void solution(TreeNode root, int level, AtomicInteger max) {
        if (null != root.left) {
            solution(root.left, level + 1, max);
        }
        if (null != root.right) {
            solution(root.right, level + 1, max);
        }
        if (level > max.get()) {
            max.set(level);
        }
    }

    /**
     * 绝了
     */
    public int maxDepthBest(TreeNode root) {
        if (root == null) {
            return 0;
        }

        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }
}
