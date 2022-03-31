package com.janwarlen.ac.treesAndGraphs;

public class PopulatingNextRightPointersinEachNode {
    public Node connect(Node root) {
        if (null == root || null == root.left) {
            return root;
        }
        root.left.next = root.right;
        if (null != root.next) {
            root.right.next = root.next.left;
        } else {
            root.right.next = null;
        }
        connect(root.left);
        connect(root.right);
        return root;
    }
}
