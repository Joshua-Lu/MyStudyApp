package com.lhf.javacommonlib.leetcode.sort.unionfind;

/**
 * Created by Joshua on 2021/1/14.
 */
public interface IUnionFind {

    int getSize();

    //查看元素p和q是否属于同一个集合
    boolean isConnected(int p, int q);

    //将p和q所属的集合合并
    void unionElements(int p, int q);

    //查找元素p所对应的集合编号
    int find(int p);
}
