package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * Created by Joshua on 2021/1/14.
 */
public class QuickUnionFind implements IUnionFind {
    int[] parent; //点的父节点
    int[] sz; //点的权

    /**
     * @param N 点的个数
     */
    public QuickUnionFind(int N) {
        parent = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
            sz[i] = 1;
        }
    }

    @Override
    public int getSize() {
        return parent.length;
    }

    @Override
    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    @Override
    public void unionElements(int p, int q) {
        int pRoot = find(p); //找到此点的根节点
        int qRoot = find(q); //同上
        if (pRoot == qRoot) return; //如果在同一通路，返回
        if (sz[pRoot] >= sz[qRoot]) {
            parent[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            parent[qRoot] = pRoot;
            sz[qRoot] += sz[pRoot];
        } //将权值小的根节点连在权值大的根节点上，并将权值相加赋给连接后的根节点
    }

    @Override
    public int find(int p) {
        int temp = p;
        while (p != parent[p]) {
            p = parent[p];
        } //一直向上找父节点，直到节点的父节点为自身，则为根节点
        while (temp != parent[temp]) //将找过的点全部连在根节点上
        {
            int temp2 = temp;
            temp = parent[temp];
            parent[temp2] = p;
        }
        return p;
    }
}
