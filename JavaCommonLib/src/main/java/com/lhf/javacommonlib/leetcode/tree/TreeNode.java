package com.lhf.javacommonlib.leetcode.tree;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

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
        System.out.println("TreeNode.createTree returned: root = [" + root + "]");
        return root;
    }

    @Override
    public String toString() {
//        return toArrayString();
        return toTreeString();
    }

    /**
     * 通过层序遍历，以数组形式返回Tree
     */
    public String toArrayString() {
        StringBuilder sb = new StringBuilder("Tree level order traversal[");
        Queue<TreeNode> deque = new LinkedList<>();
        deque.offer(this);
        while (!deque.isEmpty()) {
            TreeNode node = deque.poll();
            if (node != null) {
                sb.append(node.val).append(", ");
                deque.offer(node.left);
                deque.offer(node.right);
            } else {
                sb.append("null").append(", ");
            }
        }
        sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }

    /**
     * 通过前序遍历，以树形结构返回Tree
     */
    public String toTreeString() {
        StringBuilder result = new StringBuilder("Tree[\n");
        result.append(levelOrder(this));
        result.append("]");
        return result.toString();
    }

    private String toTreeString(TreeNode root) {
        if (root == null) {
            return "null";
        }
        StringBuilder res = new StringBuilder();
        res.append(root.val);
        String left = toTreeString(root.left);
        String right = toTreeString(root.right);
        return res.append(left).append(right).toString();
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepth(root.left);
        int rightDepth = maxDepth(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    public String levelOrder(TreeNode root) {
//        List<List<Integer>> result = new ArrayList<>();
        StringBuilder res = new StringBuilder();
        if (root == null) {
            return res.toString();
        }
        int depth = maxDepth(root);
        String minGap = "  ";
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (depth > 0 && !queue.isEmpty()) {
            for (int i = 1; i < (1 << (depth - 1)); i++) {
                res.append(minGap);
            }
            int size = queue.size();// 当前这一层有多少个结点
//            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll == null) {
                    poll = new TreeNode(Integer.MIN_VALUE, new TreeNode(Integer.MIN_VALUE), new TreeNode(Integer.MIN_VALUE));
                }
                int val = poll.val;
                if (val != Integer.MIN_VALUE) {
                    if (val > 0 && val < 10) {
                        res.append(0);
                    }
//                  list.add(poll.val);
                    res.append(val);
                } else {
                    res.append(minGap);
                }
                for (int j = 1; j < (1 << depth); j++) {
                    res.append(minGap);
                }
                queue.offer(poll.left);
                queue.offer(poll.right);
            }
//            result.add(list);
            res.append("\n");
            depth--;
        }
        return res.toString();
    }

}
