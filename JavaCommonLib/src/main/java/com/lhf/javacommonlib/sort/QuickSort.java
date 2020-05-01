package com.lhf.javacommonlib.sort;

/**
 * 快速排序
 * 时间复杂度：平均O(nlogn)，最坏O(n^2)，最好O(nlogn)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * Created by Joshua on 2020/4/30.
 */
public class QuickSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private int[] quickSort(int[] array, int start, int end) {
        if (array.length < 2 || start < 0 || end > array.length || start >= end) {
            return array;
        }
        int pivotIndex = partition(array, start, end);
        if (pivotIndex > start) {
            quickSort(array, start, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            quickSort(array, pivotIndex + 1, end);
        }
        return array;
    }

    private int partition(int[] array, int start, int end) {
        if (end - start < 1) {
            return start;
        }
        int pivotIndex = start;
        for (int i = start; i <= end; i++) {
            if (array[i] < array[start]) {
                SortUtil.swap(array, i, pivotIndex + 1);
                pivotIndex++;
            }
        }
        SortUtil.swap(array, start, pivotIndex);
        return pivotIndex;
    }
}
