package com.lhf.javacommonlib.leetcode.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Joshua on 2021/1/15.
 */
public class MaximumUnits {

    /*
    https://leetcode-cn.com/problems/maximum-units-on-a-truck/
    1710. 卡车上的最大单元数
    请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi, numberOfUnitsPerBoxi] ：
    numberOfBoxesi 是类型 i 的箱子的数量。
    numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
    整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
    返回卡车可以装载 单元 的 最大 总数。

    示例 1：
        输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
        输出：8
        解释：箱子的情况如下：
                - 1 个第一类的箱子，里面含 3 个单元。
                - 2 个第二类的箱子，每个里面含 2 个单元。
                - 3 个第三类的箱子，每个里面含 1 个单元。
        可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
        单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
    示例 2：
        输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
        输出：91


    提示：
        1 <= boxTypes.length <= 1000
        1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
        1 <= truckSize <= 106
    */

    @Test
    public void test() {
        int[][] boxTypes;
        int truckSize;
        int result;

        boxTypes = new int[][]{{1, 3}, {2, 2}, {3, 1}};
        truckSize = 4;
        result = maximumUnits1(boxTypes, truckSize);
        Assert.assertEquals(8, result);

        boxTypes = new int[][]{{2, 1}, {4, 4}, {3, 1}, {4, 1}, {2, 4}, {3, 4}, {1, 3}, {4, 3}, {5, 3}, {5, 3}};
        truckSize = 13;
        result = maximumUnits1(boxTypes, truckSize);
        Assert.assertEquals(48, result);

    }

    private int maximumUnits(int[][] boxTypes, int truckSize) {
        int length = boxTypes.length;
        System.out.println("MyClass.maximumUnits: length = [" + length + "]");
        int result = 0;

        // 该map根据key值，倒序排列
        // 其中key是每个箱子可以装载的单元数量，value是这种箱子的数量。
        Map<Integer, Integer> map = new TreeMap<>((o1, o2) -> o2 - o1);
        for (int i = 0; i < length; i++) {
            int key = boxTypes[i][1];
            int value = boxTypes[i][0];
            if (map.containsKey(key)) {
                map.put(key, map.get(key) + value);
            } else {
                map.put(key, value);
            }
        }
        System.out.println("MyClass.maximumUnits: map = [" + map + "]");

        Set<Map.Entry<Integer, Integer>> entries = map.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Integer, Integer> entry = iterator.next();
            int numberOfBoxes = entry.getValue();
            int numberOfUnitsPerBox = entry.getKey();
            System.out.println("MyClass.maximumUnits: numberOfBoxes = [" + numberOfBoxes + "]");
            System.out.println("MyClass.maximumUnits: numberOfUnitsPerBox = [" + numberOfUnitsPerBox + "]");
            System.out.println("MyClass.maximumUnits: truckSize = [" + truckSize + "]");
            if (numberOfBoxes <= truckSize) {
                result += numberOfBoxes * numberOfUnitsPerBox;
                System.out.println("MyClass.maximumUnits: result = [" + result + "]");
                truckSize -= numberOfBoxes;
            } else {
                result += truckSize * numberOfUnitsPerBox;
                System.out.println("MyClass.maximumUnits: return result = [" + result + "]");
                return result;
            }
        }
        return result;
    }

    public int maximumUnits1(int[][] boxTypes, int truckSize) {
        // 数组可以用Arrays.sort()直接排序
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int maxSum = 0;
        int currentTruckSize = 0;
        for (int[] boxType : boxTypes) {
            currentTruckSize += boxType[0];
            if (currentTruckSize <= truckSize) {
                maxSum += boxType[0] * boxType[1];
            } else {
                currentTruckSize -= boxType[0];
                maxSum += (truckSize - currentTruckSize) * boxType[1];
                break;
            }
        }
        return maxSum;
    }
}
