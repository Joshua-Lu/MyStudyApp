package com.lhf.javacommonlib.sort;

import java.util.Arrays;

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
        int pivotIndex = partition2(array, start, end);
        if (pivotIndex > start) {
            quickSort(array, start, pivotIndex - 1);
        }
        if (pivotIndex < end) {
            quickSort(array, pivotIndex + 1, end);
        }
        return array;
    }

    /**
     * 单向快排
     */
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

    /**
     * 双向快排
     * <p>
     * 从左往右找到比基准数大的，从右往左找到比基准数小的，直接交换这两个数，然后继续循环查找交换
     * 相比单向快排，会少好多次交换
     */
    private int partition2(int[] array, int start, int end) {
        if (!SortTest.randomTest)
            System.out.println("QuickSort.partition2() called with: array = [" + Arrays.toString(array) + "], start = [" + start + "], end = [" + end + "]");
        if (end - start < 1) {
            return start;
        }
        // start位置的数作为基准
        int base = array[start];
        int leftPivot = start + 1;
        int rightPivot = end;
        if (!SortTest.randomTest)
            System.out.println("QuickSort.partition2: leftPivot = [" + leftPivot + "], rightPivot = [" + rightPivot + "]");
        if (leftPivot == rightPivot) {
            if (array[leftPivot] < base) {
                SortUtil.swap(array, start, leftPivot);
            }
            if (!SortTest.randomTest)
                System.out.println("QuickSort.partition2 return: leftPivot = [" + leftPivot + "]");
            return leftPivot;
        }
        while (leftPivot < rightPivot) {
            while (leftPivot <= rightPivot && array[leftPivot] <= base) leftPivot++;
            while (leftPivot < rightPivot && array[rightPivot] > base) rightPivot--;
            if (!SortTest.randomTest)
                System.out.println("QuickSort.partition2: leftPivot = [" + leftPivot + "], rightPivot = [" + rightPivot + "]");
            if (leftPivot < rightPivot) {
                SortUtil.swap(array, leftPivot, rightPivot);
            }
        }
        SortUtil.swap(array, start, --leftPivot);
        if (!SortTest.randomTest)
            System.out.println("QuickSort.partition2 return: leftPivot = [" + leftPivot + "]");
        return leftPivot;
    }
}
