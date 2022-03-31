package com.janwarlen.ac.trees;

import java.util.ArrayList;
import java.util.List;

public class SymmetricTree {
    public static boolean isSymmetric(TreeNode root) {
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        boolean keep = true;
        for (int i = 0; keep && nodes.size() != 0; i++) {
            int end = nodes.size() - 1;
            for (int j = 0; j < end; j++, end--) {
                if (nodes.get(j) != nodes.get(end) && (null == nodes.get(j) || null == nodes.get(end))) {
                    return false;
                }
                if (null != nodes.get(j) && null != nodes.get(end) && nodes.get(j).val != nodes.get(end).val) {
                    return false;
                }
            }
            List<TreeNode> next = new ArrayList<>();
            keep = false;
            for (int j = 0; j < nodes.size(); j++) {
                if (null != nodes.get(j)) {
                    next.add(nodes.get(j).left);
                    next.add(nodes.get(j).right);
                    keep = true;
                } else {
                    next.add(null);
                    next.add(null);
                }
            }
            nodes = next;
        }
        return true;
    }

    /**
     * 代码简洁
     */
    public boolean isSymmetricBest(TreeNode root) {
        return isMirror(root, root);

    }
    public boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return true;
        if (t1 == null || t2 == null) return false;
        return (t1.val == t2.val) && isMirror(t1.left, t2.right) && isMirror(t1.right, t2.left);
    }

    public static void main(String[] args) {
        /*TreeNode root = new TreeNode(1);
        TreeNode rl = new TreeNode(2);
        TreeNode rr = new TreeNode(2);
        root.left = rl;
        root.right = rr;
        TreeNode rll = new TreeNode(3);
        TreeNode rlr = new TreeNode(4);
        rl.left = rll;
        rl.right = rlr;
        TreeNode rrl = new TreeNode(4);
        TreeNode rrr = new TreeNode(3);
        rr.left = rrl;
        rr.right = rrr;*/
        /*TreeNode root = new TreeNode(1);
        TreeNode rl = new TreeNode(2);
        TreeNode rr = new TreeNode(2);
        root.left = rl;
        root.right = rr;
        TreeNode rlr = new TreeNode(3);
        rl.right = rlr;
        TreeNode rrr = new TreeNode(3);
        rr.right = rrr;*/
        TreeNode root = new TreeNode(1);
        TreeNode rl = new TreeNode(2);
        TreeNode rr = new TreeNode(3);
        root.left = rl;
        root.right = rr;
        System.out.println(isSymmetric(root));
    }
}
