package com.lhf.javacommonlib.sort;

/**
 * 插入排序
 * <p>
 * 时间复杂度：平均O(n^2)，最坏O(n^2)，最好O(n)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 * <p>
 * 样本小且基本有序的时候效率高
 * <p>
 * Created by Joshua on 2020/4/30.
 */
public class InsertionSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        int current;
        for (int i = 0; i < array.length - 1; i++) {
            current = array[i + 1];
            int preIndex = i;
            while (preIndex >= 0 && current < array[preIndex]) {// 当前数字比前一个位置的数小
                array[preIndex + 1] = array[preIndex];// 前一位置数字后移
                preIndex--;// 继续与再前一位数字比较
            }
            // 找到当前数字比前一个位置的数大
            array[preIndex + 1] = current;
        }
        return array;
    }
}
