package com.janwarlen.ac.treesAndGraphs;

public class KthSmallestElementInABST {
    int nums = 1;

    public int kthSmallest(TreeNode root, int k) {
        if (null == root) {
            return -1;
        }
        if (null != root.left) {
            int i = kthSmallest(root.left, k);
            if (i > -1 ) {
                return i;
            }
        }
        if (nums == k) {
           return root.val;
        } else {
            nums ++;
            return kthSmallest(root.right, k);
        }
    }
}
