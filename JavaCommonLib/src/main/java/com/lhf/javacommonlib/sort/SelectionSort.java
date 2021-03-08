package com.lhf.javacommonlib.sort;

import com.lhf.javacommonlib.utils.CommonUtils;

/**
 * 选择排序
 * <p>
 * 时间复杂度：平均O(n^2)，最坏O(n^2)，最好O(n^2)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * <p>
 * 基本不用，不稳定
 * <p>
 * Created by Joshua on 2020/4/30.
 */
public class SelectionSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[j] < array[minIndex]) {// 找到更小的数
                    minIndex = j;// 保存该数的索引
                }
            }
            // 本次循环找到的最小值，与i位置的数交换
            CommonUtils.swap(array, i, minIndex);
        }
        return array;
    }
}
