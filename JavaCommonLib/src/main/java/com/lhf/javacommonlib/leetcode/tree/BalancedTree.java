package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * Created by Joshua on 2021/3/16.
 */
public class BalancedTree {
    /*
    https://leetcode-cn.com/problems/balanced-binary-tree/
    110. 平衡二叉树
    给定一个二叉树，判断它是否是高度平衡的二叉树。

    本题中，一棵高度平衡二叉树定义为：

    一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。

    示例 1：
        输入：root = [3,9,20,null,null,15,7]
        输出：true
    示例 2：
        输入：root = [1,2,2,3,3,null,null,4,4]
        输出：false
    示例 3：
        输入：root = []
        输出：true


    提示：
        树中的节点数在范围 [0, 5000] 内
        -104 <= Node.val <= 104*/

    @Test
    public void test() {
        TreeNode root;
        boolean res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{3, 9, 20, null, null, 15, 7});
        res = isBalanced(root);
        Assert.assertTrue(res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{1, 2, 2, 3, 3, null, null, 4, 4});
        res = isBalanced(root);
        Assert.assertFalse(res);

        // 示例 3
        root = TreeNode.createTree(new Integer[]{});
        res = isBalanced(root);
        Assert.assertTrue(res);

    }

    public boolean isBalanced(TreeNode root) {
//        return isBalanced1(root);
//        return isBalanced2(root);
        return isBalanced3(root);
    }

    /**
     * 递归法
     */
    public boolean isBalanced1(TreeNode root) {
        return getHeight(root) != -1;
    }

    private int getHeight(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = getHeight(root.left);
        if (leftHeight == -1) {
            return -1;
        }
        int rightHeight = getHeight(root.right);
        if (rightHeight == -1) {
            return -1;
        }
        // 左右子树高度差大于1，return -1表示已经不是平衡树了
        if (Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }

    /**
     * 迭代法，效率较低，计算高度时会重复遍历
     * <p>
     * 时间复杂度：O(n^2)
     */
    public boolean isBalanced2(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            // 右结点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                // 比较左右子树高度差，输出
                if (Math.abs(getHeight2(inNode.left) - getHeight2(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = inNode.right;// 右结点还没遍历，遍历右结点
            }
        }
        return true;
    }

    /**
     * 层序遍历，求结点的高度
     */
    public int getHeight2(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int depth = 0;
        while (!deque.isEmpty()) {
            int size = deque.size();
            depth++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = deque.poll();
                if (poll.left != null) {
                    deque.offer(poll.left);
                }
                if (poll.right != null) {
                    deque.offer(poll.right);
                }
            }
        }
        return depth;
    }

    /**
     * 优化迭代法，针对暴力迭代法的getHeight方法做优化，利用TreeNode.val来保存当前结点的高度，这要就不会有重复遍历
     * 获取高度算法时间复杂度可以降到O(1)，总的时间复杂度降为O(n)。
     * <p>
     * 时间复杂度：O(n)
     * <p>
     * isBalanced3()跟isBalanced2()是一样的，只是getHeight方法不同。
     */
    public boolean isBalanced3(TreeNode root) {
        if (root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            TreeNode inNode = stack.peek();
            // 右结点为null或已经遍历过
            if (inNode.right == null || inNode.right == pre) {
                // 比较左右子树高度差，输出
                if (Math.abs(getHeight3(inNode.left) - getHeight3(inNode.right)) > 1) {
                    return false;
                }
                stack.pop();
                pre = inNode;
                root = null;// 当前结点下，没有要遍历的结点了
            } else {
                root = inNode.right;// 右结点还没遍历，遍历右结点
            }
        }
        return true;
    }

    /**
     * 求结点的高度
     */
    public int getHeight3(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = root.left != null ? root.left.val : 0;
        int rightHeight = root.right != null ? root.right.val : 0;
        int height = Math.max(leftHeight, rightHeight) + 1;
        root.val = height;// 用TreeNode.val来保存当前结点的高度
        return height;
    }
}
