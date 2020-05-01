package com.lhf.javacommonlib.sort;

/**
 * 堆排序
 * 时间复杂度：平均O(nlogn)，最坏O(nlogn)，最好O(nlogn)
 * 空间复杂度：O(1)
 * 稳定性：不稳定
 * Created by Joshua on 2020/5/1.
 */
public class HeapSort implements ISort {
    private int length;

    @Override
    public int[] sort(int[] array) {
        length = array.length;
        if (length < 2) {
            return array;
        }
        // 构建大顶堆
        buildMaxHeap(array);
        // 循环将堆顶与堆末位交换，并重新调整堆
        while (length > 0) {
            SortUtil.swap(array, 0, length - 1);
            length--;
            adjustHeap(array, 0);
        }
        return array;
    }

    private void adjustHeap(int[] array, int i) {
        int maxIndex = i;
        int leftIndex = 2 * i + 1;
        int rightIndex = 2 * i + 2;
        // 如果有左子树，且左子树大于父节点，则将最大指针指向左子树
        if (leftIndex < length && array[leftIndex] > array[maxIndex]) {
            maxIndex = leftIndex;
        }
        // 如果有右子树，且右子树大于父节点，则将最大指针指向右子树
        if (rightIndex < length && array[rightIndex] > array[maxIndex]) {
            maxIndex = rightIndex;
        }
        // 如果父节点不是最大值，则将父节点与最大值交换，并且递归调整与父节点交换的位置。
        if (maxIndex != i) {
            SortUtil.swap(array, i, maxIndex);
            adjustHeap(array, maxIndex);
        }
    }

    private void buildMaxHeap(int[] array) {
        for (int i = (length / 2 - 1); i >= 0; i--) {
            adjustHeap(array, i);
        }
    }
}
