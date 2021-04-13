package com.lhf.javacommonlib.sort;

import com.lhf.javacommonlib.utils.CommonUtils;

import java.util.Arrays;

/**
 * 一段时候后，重新独立写的
 *
 * @author Joshua
 * @date 2021/4/13 23:14
 */
public class QuickSort1 implements ISort {
    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        quickSort(array, 0, array.length - 1);
        return array;
    }

    /**
     * 快排[start,end]区间内的数
     */
    private int[] quickSort(int[] array, int start, int end) {
        if (end - start < 1) {
            return array;
        }
        if (end - start == 1) {
            if (array[end] < array[start]) {
                CommonUtils.swap(array, start, end);
            }
            return array;
        }
        int divideIndex = divide(array, start, end);
        System.out.println("QuickSort1.quickSort: divideIndex = [" + divideIndex + "]");
        quickSort(array, start, divideIndex - 1);
        quickSort(array, divideIndex + 1, end);
        return array;
    }

    private int divide(int[] array, int start, int end) {
        System.out.println("QuickSort1.divide() called with: array = [" + Arrays.toString(array) + "], start = [" + start + "], end = [" + end + "]");
        int baseIndex = start++;
        int base = array[baseIndex];
        while (start < end) {
            while (start < end && array[start] < base) {
                start++;
            }
            while (start < end && array[end] >= base) {
                end--;
            }
            CommonUtils.swap(array, start, end);
        }
        if (array[start] >= base) {
            CommonUtils.swap(array, --start, baseIndex);
        } else {
            CommonUtils.swap(array, start, baseIndex);
        }
        return start;
    }


}
