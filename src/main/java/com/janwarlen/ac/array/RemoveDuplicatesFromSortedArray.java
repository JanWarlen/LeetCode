package com.janwarlen.ac.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RemoveDuplicatesFromSortedArray {

    /**
     * ac,耗时过长，pass
     * 核心理念是利用最大值作为边界去逐步缩小待筛选数组
     * 每次需要重新排列耗时较多
     */
    public static int removeDuplicates_1(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return null == nums ? 0 : nums.length;
        }
        int idx = nums.length - 1;
        for (int i = 1; i < nums.length && i <= idx; i++) {
            if (nums[i] == nums[i - 1]) {
                // 需要去重
                swap(nums, idx, i);
                // 需要在指定范围内重新进行排序
                if ((--idx) >= i) {
                    Arrays.sort(nums, i, idx + 1);
                }
                i--;
            }
        }
        return idx + 1;
    }

    private static void swap(int[] nums, int idx, int i) {
        nums[i] += nums[idx];
        nums[idx] = nums[i] - nums[idx];
        nums[i] -= nums[idx];
    }

    public static int removeDuplicates(int[] nums) {
        if (null == nums || nums.length <= 1) {
            return null == nums ? 0 : nums.length;
        }
        List<Integer> eleIdxs = new ArrayList<>(nums.length);
        eleIdxs.add(0);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                eleIdxs.add(i);
            }
        }
        int size = eleIdxs.size();
        for (int i = 0; i < size; i++) {
            nums[i] = nums[eleIdxs.get(i)];
        }
        return size;
    }

    /**
     * 与我的思路大体一致，不过时间和空间更加优化
     * num是不重复的元素索引
     * 因为整体是升序的，因此核心思路在于遍历时，遇到重复的继续，遇到不重复的将元素赋值给num索引
     * 如诗般优美的代码
     */
    public static int removeDuplicatesBest(int[] nums) {
        int num = 0;
        for(int i = 1; i < nums.length; i++)
        {
            if( nums[num] != nums[i]){
                nums[++num] =  nums[i];
            }
        }
        return num + 1;
    }

    public static void main(String[] args) {
        System.out.println(removeDuplicatesBest(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}));
        System.out.println(removeDuplicatesBest(new int[]{1, 1}));
        System.out.println(removeDuplicatesBest(new int[]{1, 1, 1}));
        System.out.println(removeDuplicatesBest(new int[]{1, 1, 2}));
        System.out.println(removeDuplicatesBest(new int[]{1, 1, 1, 2}));
        System.out.println(removeDuplicatesBest(new int[]{1, 2}));
    }
}
