package com.janwarlen.ac.treesAndGraphs;

import java.util.HashMap;
import java.util.Map;

public class ConstructBinaryTreeFromPreorderAndInorderTraversal {
    int preorderIndex = 0;
    Map<Integer, Integer> inorderIndex = new HashMap<>();


    public TreeNode buildTree(int[] preorder, int[] inorder) {
        for (int i = 0; i < inorder.length; i++) {
            inorderIndex.put(inorder[i], i);
        }

        return arrayToTree(preorder, 0, preorder.length-1);
    }

    private TreeNode arrayToTree(int[] preorder, int left, int right) {
        if (left > right) {
            return null;
        }
        int rootValue = preorder[preorderIndex++];
        TreeNode root = new TreeNode(rootValue);

        root.left = arrayToTree(preorder, left, inorderIndex.get(rootValue) - 1);
        root.right = arrayToTree(preorder, inorderIndex.get(rootValue) + 1, right);

        return root;
    }
}
