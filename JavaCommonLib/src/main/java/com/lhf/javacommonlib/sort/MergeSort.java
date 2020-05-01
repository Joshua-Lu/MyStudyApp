package com.lhf.javacommonlib.sort;

import java.util.Arrays;

/**
 * 归并排序（采用分治法）
 * <p>
 * 时间复杂度：平均O(nlogn)，最坏O(nlogn)，最好O(nlogn)
 * 空间复杂度：O(n)
 * 稳定性：稳定
 * Created by Joshua on 2020/4/30.
 */
public class MergeSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        int length = array.length;
        if (length < 2) {
            return array;
        }
        int mid = length / 2;
        int[] left = Arrays.copyOfRange(array, 0, mid);
        int[] right = Arrays.copyOfRange(array, mid, length);
        return merge(sort(left), sort(right));
    }

    private int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        int i = 0;
        int j = 0;
        for (int index = 0; index < result.length; index++) {
            if (i >= left.length) {
                result[index] = right[j++];
            } else if (j >= right.length) {
                result[index] = left[i++];
            } else if (left[i] < right[j]) {
                result[index] = left[i++];
            } else {
                result[index] = right[j++];
            }
        }
        return result;
    }
}
