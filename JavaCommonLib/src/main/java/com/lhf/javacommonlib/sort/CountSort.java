package com.lhf.javacommonlib.sort;

/**
 * 计数排序
 * <p>
 * 适用量大但范围小并且值为整数的情况，如一万人按年龄排序
 * <p>
 * 时间复杂度：平均O(n)，最坏O(n)，最好O(n)
 * 空间复杂度：O(n)
 * 稳定性：不稳定（可以优化成稳定的）
 * <p>
 * Created by Joshua on 2021/3/2 23:40
 */
public class CountSort implements ISort {

    private boolean stable = true;// 稳定性

    @Override
    public int[] sort(int[] array) {
        // 数组值的范围是[0, SortTest.ARRAY_VALUE_MAX)
        int[] counts = new int[SortTest.ARRAY_VALUE_MAX];
        // 数组的值作为下标，出现次数作为值
        for (int i : array) {
            counts[i]++;
        }
        if (!stable) {
            // 这个算法是不稳定的，可以通过使用累加数组，改成稳定的
            int index = 0;
            for (int i = 0; i < counts.length; i++) {
                for (int j = 0; j < counts[i]; j++) {
                    array[index++] = i;
                }
            }
            return array;

        } else {
            // 使用累加数组，从1位置开始，将当前位置值改为当前位置的值+前一个位置的值
            for (int i = 1; i < counts.length; i++) {
                counts[i] += counts[i - 1];
            }
            // 得到的累加数组的值代表，下标对应数字的最后一个数，在排序后的数组的位置
            // 这样就可以从后往前遍历原数组，将数直接放到对应位置，再将累加数组对应的值减一，再继续遍历
            int[] result = new int[array.length];
            for (int i = array.length - 1; i >= 0; i--) {
                result[--counts[array[i]]] = array[i];
            }
            return result;

        }


    }
}
