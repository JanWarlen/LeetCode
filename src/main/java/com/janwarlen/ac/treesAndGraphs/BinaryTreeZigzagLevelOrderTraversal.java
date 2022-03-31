package com.janwarlen.ac.treesAndGraphs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (null == root) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> parents = new ArrayList<>();
        parents.add(root);
        boolean reverse = false;
        while (!parents.isEmpty()) {
            List<Integer> tmp = new ArrayList<>();
            List<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode parent : parents) {
                if (null != parent) {
                    tmp.add(parent.val);
                    nextLevel.add(parent.left);
                    nextLevel.add(parent.right);
                }
            }
            parents.clear();
            parents.addAll(nextLevel);
            if (reverse) {
                Collections.reverse(tmp);
            }
            reverse = !reverse;
            if (!tmp.isEmpty()) {
                res.add(tmp);
            }
        }
        return res;
    }
}
