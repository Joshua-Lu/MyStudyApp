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

    /**
     * 构建二叉树
     *
     * @param data 二叉树层序遍历对应的数组，孩子结点为空时用null表示
     * @return 二叉树的根结点
     */
    public static TreeNode createTree(Integer[] data) {
        System.out.println("TreeNode.createTree() called with: data = [" + Arrays.toString(data) + "]");
        if (data == null || data.length == 0) {
            return null;
        }
        Deque<TreeNode> deque = new ArrayDeque<>();
        TreeNode root = new TreeNode(data[0]);
        deque.offer(root);
        int index = 1;
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (index == data.length) {
                break;
            }
            // 设置左子树
            Integer value = data[index++];
            if (value != null) {
                TreeNode leftNode = new TreeNode(value);
                node.left = leftNode;
                deque.offer(leftNode);
            }
            if (index == data.length) {
                break;
            }
            // 设置右子树
            value = data[index++];
            if (value != null) {
                TreeNode rightNode = new TreeNode(value);
                node.right = rightNode;
                deque.offer(rightNode);
            }
        }
        return root;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Tree level order traversal[");
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(this);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node != null) {
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
