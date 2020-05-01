package com.lhf.javacommonlib.sort;

/**
 * 冒泡排序
 * 时间复杂度：平均O(n^2)，最坏O(n^2)，最好O(n)
 * 空间复杂度：O(1)
 * 稳定性：稳定
 * Created by Joshua on 2020/4/30.
 */
public class BubbleSort implements ISort {
    @Override
    public int[] sort(int[] array) {
        if (array.length < 2) {
            return array;
        }
        boolean swaped = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    SortUtil.swap(array, j, j + 1);
                    swaped = true;
                }
            }
            // 遍历一遍，若没有要交换的，说明该数组本来就是排好了的
            // 则不用继续遍历了，直接返回原数组即可
            // 保证了在该最好的情况下时间复杂度为O(n)
            if (!swaped) {
                return array;
            }
        }
        return array;
    }

}
