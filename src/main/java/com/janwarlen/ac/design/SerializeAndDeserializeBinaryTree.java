package com.janwarlen.ac.design;

import java.util.*;

// Serialize and Deserialize Binary Tree
public class SerializeAndDeserializeBinaryTree {

    /**
     * 思路一致，简化了序列化后字符串的复杂度，序列化后 xx,xx,xx为一组保持格式为当前值，left值，right值
     */
    public String serializePro(TreeNode root) {
        return serial(new StringBuilder(), root).toString();
    }

    // Generate preorder string
    // 1, 2,#,# ,3,4,#,#,5,#,#
    private StringBuilder serial(StringBuilder str, TreeNode root) {
        if (root == null) return str.append("#");
        str.append(root.val).append(",");
        serial(str, root.left).append(",");
        serial(str, root.right);
        return str;
    }

    public TreeNode deserializePro(String data) {
        return deserial(new LinkedList<>(Arrays.asList(data.split(","))));
    }

    // Use queue to simplify position move
    private TreeNode deserial(Queue<String> q) {
        String val = q.poll();
        if ("#".equals(val)) return null;
        TreeNode root = new TreeNode(Integer.parseInt(val));
        root.left = deserial(q);
        root.right = deserial(q);
        return root;
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(root.val);
        sb.append("[");
        sb.append(serialize(root.left));
        sb.append(",");
        sb.append(serialize(root.right));
        sb.append("]");
        return sb.toString();
    }

    /******************************************************************/

    // Decodes your encoded data to tree.
    // 1[2[null,null],3[4[null,null],5[null,null]]]
    public TreeNode deserialize(String data) {
        if (null == data || data.isEmpty() || "null".equals(data)) {
            return null;
        }
        TreeNode root = new TreeNode();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < data.length(); i++) {
            if (47 < data.charAt(i) && data.charAt(i) < 58 || 45 == data.charAt(i)) {
                sb.append(data.charAt(i));
            } else if (91 == data.charAt(i)) {
                deserialize(root, data.substring(i + 1));
                break;
            }
        }
        root.val = Integer.parseInt(sb.toString());
        return root;
    }

    public int deserialize(TreeNode node, String data) {
        boolean left = true;
        StringBuilder sb = new StringBuilder();
        TreeNode tmp;
        for (int i = 0; i < data.length(); i++) {
            if (47 < data.charAt(i) && data.charAt(i) < 58 || 45 == data.charAt(i)) {
                // 0,1,2,3,4,5,6,7,8,9
                sb.append(data.charAt(i));
            } else if (91 == data.charAt(i)) {
                // [
                tmp = new TreeNode(Integer.parseInt(sb.toString()));
                if (left) {
                    node.left = tmp;
                    left = false;
                } else {
                    node.right = tmp;
                }
                i += deserialize(tmp, data.substring(i + 1));
            } else if (110 == data.charAt(i)) {
                // null
                tmp = null;
                i += 3;
                if (left) {
                    node.left = tmp;
                    left = false;
                } else {
                    node.right = tmp;
                }
            } else if (44 == data.charAt(i)) {
                // ,
                sb = new StringBuilder();
                left = false;
            } else if (93 == data.charAt(i)) {
                // ]
                return i + 1;
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode al = new TreeNode(2);
        TreeNode ar = new TreeNode(3);
        root.left = al;
        root.right = ar;
        TreeNode arl = new TreeNode(4);
        TreeNode arr = new TreeNode(5);
        ar.left = arl;
        ar.right = arr;
        SerializeAndDeserializeBinaryTree test = new SerializeAndDeserializeBinaryTree();

        System.out.println(test.serializePro(root));
        System.out.println(test.deserialize("1[2[null,null],3[4[null,null],5[null,null]]]"));
    }
}
