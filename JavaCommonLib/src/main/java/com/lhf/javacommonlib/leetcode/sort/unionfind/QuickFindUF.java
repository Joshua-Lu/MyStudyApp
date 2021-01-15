package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * quick-find实现
 * <p>
 * 思路：
 * 数组中的每个元素的下标为触点，每个元素的值为触点值，触点值相等的的触点在一个连接上所以：
 * 1. 要判断两个触点是否在一个连接上，只需要判断两个触点对应的触点值是否相等即可
 * 2. 要把不在一个连接上的触点连接起来，只需要让两个触点的触点值相等即可。
 * <p>
 * 优缺点：
 * find操作很快，但是union操作每次都要遍历整个数组，效率低下
 * <p>
 * Created by Joshua on 2021/1/15.
 */
public class QuickFindUF extends AbstractUnionFind {

    public QuickFindUF(int size) {
        super(size);
    }

    @Override
    public int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pRoot) {
                id[i] = qRoot;
            }
        }
        count--;
    }
}
