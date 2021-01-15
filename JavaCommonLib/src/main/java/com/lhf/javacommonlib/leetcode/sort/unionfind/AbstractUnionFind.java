package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * Created by Joshua on 2021/1/15.
 */
public abstract class AbstractUnionFind implements IUnionFind {
    protected int[] id;
    protected int count;

    /**
     * @param size 点的个数
     */
    public AbstractUnionFind(int size) {
        this.count = size;
        id = new int[size];
        // 初始化id数组，都指向自己，互不连通
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public abstract void union(int p, int q);

    public abstract int find(int p);
}
