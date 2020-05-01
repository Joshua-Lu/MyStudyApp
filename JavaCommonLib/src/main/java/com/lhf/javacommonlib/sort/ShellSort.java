package com.lhf.javacommonlib.sort;

/**
 * 希尔排序
 * 希尔排序也是一种插入排序，它是简单插入排序经过改进之后的一个更高效的版本，也称为缩小增量排序，
 * 同时该算法是冲破O(n2）的第一批算法之一。
 * <p>
 * 时间复杂度：最坏O(n^2)，最好O(n^1.3)(怎么算出的？？)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 * Created by Joshua on 2020/4/30.
 */
public class ShellSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int length = array.length;
        int current;
        int gap = length / 2;// 分组的间隔
        while (gap > 0) {
            // 分组进行插入排序
            for (int i = gap; i < length; i++) {
                current = array[i];
                int preIndex = i - gap;
                while (preIndex >= 0 && current < array[preIndex]) {
                    array[preIndex + gap] = array[preIndex];
                    preIndex -= gap;
                }
                array[preIndex + gap] = current;
            }
            gap /= 2;
        }
        return array;
    }
}
