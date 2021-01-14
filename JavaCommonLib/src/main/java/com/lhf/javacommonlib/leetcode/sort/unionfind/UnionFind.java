package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * 效率较低
 */
public class UnionFind implements IUnionFind {
    //id 这个数组中并没有存储数据的值，而是存储了数据所在的集合编号
    private int[] id;

    public UnionFind(int size) {
        id = new int[size];
        for (int i = 0; i < id.length; i++) {
            id[i] = i;
        }
    }

    @Override
    public int getSize() {
        return id.length;
    }

    //查找元素p所对应的集合编号
    @Override
    public int find(int p) {
        if (p < 0 || p >= id.length) {
            throw new IllegalArgumentException("p is out of bound.");
        }
        return id[p];
    }

    //查看元素p和q是否属于同一个集合
    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    //将p和q所属的集合合并
    @Override
    public void unionElements(int p, int q) {
        int pID = find(p);
        int qID = find(q);

        if (pID == qID) {
            return;
        }

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pID) {
                id[i] = qID;
            }
        }
    }
}
