package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * quick-union实现
 * <p>
 * 思路：
 * 采用树的结构，数组的每个元素下标为触点，每个元素的值为另外一个触点，最终有一个触点对应的值为他自己，这个触点就是根触点，所以：
 * 1. 判断两个触点是不是再一个连接上，只需要判断两个触点的根触点是不是一样即可
 * 2. 连接两个不在一个连接上的触点只需要把一个触点的根触点指向另外一个触点的根触点即可
 * <p>
 * 优缺点：
 * union操作很快，但是find操作效率在极端的情况（一个线型的树）效率很慢
 * <p>
 * Created by Joshua on 2021/1/15.
 */
public class QuickUnionUF extends AbstractUnionFind {

    public QuickUnionUF(int size) {
        super(size);
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        id[pRoot] = qRoot;
        count--;
    }

    @Override
    public int find(int p) {
        while (p != id[p]) {
            p = id[p];
        }
        return p;
    }
}
