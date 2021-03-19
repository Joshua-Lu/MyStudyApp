package com.lhf.javacommonlib.leetcode.tree;

import org.junit.Test;

/**
 * Created by Joshua on 2021/3/11 23:57
 */
public class TreeNodeTest {

    @Test
    public void createTree() {
        Integer[] data = {1, null, 2, 3, 4, null, 5, 6, 7, 8, null, null, 9};
//        Integer[] data = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        TreeNode root = TreeNode.createTree(data);
        System.out.println("TreeNode.test: root = [" + root + "]");
    }
}