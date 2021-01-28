package com.lhf.javacommonlib.leetcode.array;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by Joshua on 2021/1/25.
 */
public class RemoveElement {

    /*
    https://leetcode-cn.com/problems/remove-element/
    27. 移除元素
    给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
    不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
    元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。

    示例 1:
        给定 nums = [3,2,2,3], val = 3,
        函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
        你不需要考虑数组中超出新长度后面的元素。

    示例 2:
        给定 nums = [0,1,2,2,3,0,4,2], val = 2,
        函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。
        注意这五个元素可为任意顺序。
        你不需要考虑数组中超出新长度后面的元素。

    说明:
    为什么返回数值是整数，但输出的答案是数组呢?
    请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
    你可以想象内部操作如下:

    // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
    int len = removeElement(nums, val);

    // 在函数里修改输入数组对于调用者是可见的。
    // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
    for (int i = 0; i < len; i++) {
        print(nums[i]);
    }
    */

    @Test
    public void test() {
        int[] nums;
        int val;
        int result;

        // 示例 1
        nums = new int[]{3, 2, 2, 3};
        val = 3;
        result = removeElement(nums, val);
        System.out.println("RemoveElement.test: result = [" + result + "]");
        System.out.println("RemoveElement.test: nums = [" + Arrays.toString(nums) + "]");
        Assert.assertEquals(2, result);

        // 示例 2
        nums = new int[]{0, 1, 2, 2, 3, 0, 4, 2};
        val = 2;
        result = removeElement(nums, val);
        System.out.println("RemoveElement.test: result = [" + result + "]");
        System.out.println("RemoveElement.test: nums = [" + Arrays.toString(nums) + "]");
        Assert.assertEquals(5, result);

        // 示例 3
        nums = new int[]{1};
        val = 1;
        result = removeElement(nums, val);
        System.out.println("RemoveElement.test: nums = [" + Arrays.toString(nums) + "]");
        Assert.assertEquals(0, result);

        // 示例 4
        nums = new int[]{4, 5};
        val = 5;
        result = removeElement(nums, val);
        System.out.println("RemoveElement.test: nums = [" + Arrays.toString(nums) + "]");
        Assert.assertEquals(1, result);

    }

    public int removeElement(int[] nums, int val) {
//        return removeElement1(nums, val);
        return removeElement2(nums, val);
    }

    /**
     * 思路：从左往右，找到值等于val的位置，然后从右往左，找到值不等于val的位置，若找到了，则交换i和j位置上的数字
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    private int removeElement1(int[] nums, int val) {
        System.out.println("RemoveElement.removeElement() called with: nums = [" + Arrays.toString(nums) + "], val = [" + val + "]");
        int length;
        if (nums == null || (length = nums.length) == 0) {
            return 0;
        }
        int j = length - 1;
        for (int i = 0; i <= j; i++) {
            // 从左往右，找到值等于val的位置
            if (nums[i] == val) {
                // 从右往左，找到值不等于val的位置
                while (j > i && nums[j] == val) {
                    length--;// 结果长度减一
                    j--;
                }
                int tmp = nums[j];
                // 若找到了，则交换i和j位置上的数字
                if (tmp != val) {
                    nums[j] = nums[i];
                    nums[i] = tmp;
                    j--;
                }
                length--;// 结果长度减一
            }
        }
        System.out.println("RemoveElement.removeElement() returned: " + length);
        return length;
    }

    /**
     * 思路：快慢指针
     * 参考：https://mp.weixin.qq.com/s/wj0T-Xs88_FHJFwayElQlA
     * TODO：该链接里还有几道使用快慢指针方法的题
     * <p>
     * 时间：O(n)
     * 空间：O(1)
     */
    private int removeElement2(int[] nums, int val) {
        int fastIndex = 0;
        int slowIndex;
        for (slowIndex = 0; fastIndex < nums.length; fastIndex++) {
            if (nums[fastIndex] != val) {
                nums[slowIndex] = nums[fastIndex];
                slowIndex++;
            }
        }
        return slowIndex;
    }
}
