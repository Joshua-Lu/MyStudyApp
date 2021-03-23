package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

/**
 * Created by Joshua on 2021/3/23.
 */
public class FindMode {
    /*
    https://leetcode-cn.com/problems/find-mode-in-binary-search-tree/
    501. 二叉搜索树中的众数
    给定一个有相同值的二叉搜索树（BST），找出 BST 中的所有众数（出现频率最高的元素）。

    假定 BST 有如下定义：

    结点左子树中所含结点的值小于等于当前结点的值
            结点右子树中所含结点的值大于等于当前结点的值
    左子树和右子树都是二叉搜索树
    例如：
    给定 BST [1,null,2,2],

           1
            \
             2
            /
           2
    返回[2].

    提示：如果众数超过1个，不需考虑输出顺序

    进阶：你可以不使用额外的空间吗？（假设由递归产生的隐式调用栈的开销不被计算在内）*/

    @Test
    public void test() {
        TreeNode root;
        int[] exp, res;

        // 示例 1
        root = TreeNode.createTree(new Integer[]{1, null, 2, 2});
        exp = new int[]{2};
        res = findMode(root);
        Assert.assertArrayEquals(exp, res);

        // 示例 2
        root = TreeNode.createTree(new Integer[]{4, 2, 6, 2, 3, 5, 7});
        exp = new int[]{2};
        res = findMode(root);
        Assert.assertArrayEquals(exp, res);

    }

    ArrayList<Integer> resList;
    int maxCount;
    int count;
    TreeNode pre;

    public int[] findMode(TreeNode root) {
        resList = new ArrayList<>();
        maxCount = 0;
        count = 0;
        pre = null;
        findMode1(root);
        int[] res = new int[resList.size()];
        for (int i = 0; i < resList.size(); i++) {
            res[i] = resList.get(i);
        }
        return res;
    }

    public void findMode1(TreeNode root) {
        if (root == null) {
            return;
        }
        findMode1(root.left);

        int rootValue = root.val;
        // 计数
        if (pre == null || rootValue != pre.val) {
            count = 1;
        } else {
            count++;
        }
        // 更新resList和maxCount
        if (count > maxCount) {
            resList.clear();
            resList.add(rootValue);
            maxCount = count;
        } else if (count == maxCount) {
            resList.add(rootValue);
        }
        pre = root;

        findMode1(root.right);
    }
}
