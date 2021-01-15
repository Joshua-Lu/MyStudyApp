package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * 加权的quick-union实现
 * <p>
 * 思路：
 * quick-union的实现实际上是对树的操作，而树的层级越低，效率就越高，
 * 当一个层级很高的数连接到一个层级很低的树上的时候，就会导致效率很低。
 * 所以我们再增加一个数组记录每个连接的触点数，我们连接的时候只把触点少的树连接到触点多的树上就能提高效率了。
 * <p>
 * 优缺点：
 * 加权的quick-union算法，相比quick-find和quick-union，是三种算法中最理想的一种。
 * <p>
 * Created by Joshua on 2021/1/14.
 */
public class WeightQuickUnionUF extends QuickUnionUF {
    int[] sz; //点的权

    public WeightQuickUnionUF(int size) {
        super(size);
        sz = new int[size];
        for (int i = 0; i < size; i++) {
            sz[i] = 1;
        }
    }

    @Override
    public void union(int p, int q) {
        int pRoot = find(p); //找到此点的根节点
        int qRoot = find(q); //同上
        if (pRoot == qRoot) return; //如果在同一通路，返回

        if (sz[pRoot] >= sz[qRoot]) {
            id[qRoot] = pRoot;
            sz[pRoot] += sz[qRoot];
        } else {
            id[pRoot] = qRoot;
            sz[qRoot] += sz[pRoot];
        } //将权值小的根节点连在权值大的根节点上，并将权值相加赋给连接后的根节点
        count--;
    }

    @Override
    public int find(int p) {
        int temp = p;
        while (p != id[p]) {
            p = id[p];
        } //一直向上找父节点，直到节点的父节点为自身，则为根节点

        // 路径压缩
        while (temp != id[temp]) //将找过的点全部连在根节点上
        {
            int temp2 = temp;
            temp = id[temp];
            id[temp2] = p;
        }
        return p;
    }
}
