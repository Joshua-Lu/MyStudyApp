package com.lhf.javacommonlib.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

/**
 * Definition for a binary tree node
 * <p>
 * Created by Joshua on 2021/3/11 23:00
 */
public class TreeNode {
    Integer val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(Integer val) {
        this.val = val;
    }

    TreeNode(Integer val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode createTree(Integer[] data) {
        System.out.println("TreeNode.createTree() called with: data = [" + Arrays.toString(data) + "]");
        if (data == null || data.length == 0) {
            return new TreeNode();
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(data[0]);
        deque.offer(root);
        int index = 1;
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
//            System.out.println("TreeNode.createTree: node.val = [" + node.val + "]");
            if (node.val == null) {
                continue;
            }
            if (index == data.length) {
                break;
            }
            // 设置左子树
            Integer value = data[index++];
//            if (value != null) {
            TreeNode leftNode = new TreeNode(value);
            node.left = leftNode;
            deque.offer(leftNode);
//            }
            if (index == data.length) {
                break;
            }
            // 设置右子树
            value = data[index++];
//            if (value != null) {
            TreeNode rightNode = new TreeNode(value);
            node.right = rightNode;
            deque.offer(rightNode);
//            }
        }
        return root;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tree[");
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(this);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node.val != null) {
                sb.append(node.val).append(", ");
                if (node.left != null) {
                    deque.offer(node.left);
                }
                if (node.right != null) {
                    deque.offer(node.right);
                }
            } else {
                sb.append("null").append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
}
