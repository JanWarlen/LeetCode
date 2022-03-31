package com.janwarlen.ac.array;

import java.util.Arrays;

public class SingleNumber {

    public int singleNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {
                return nums[i];
            } else {
                i++;
            }
        }
        return nums[nums.length];
    }

    /**
     * 按位异或,核心就是一个数和不同的数异或运算，结果会包含两个数，如果此时和其中一个数再次异或运算，则会根据特性消除
     * 最后剩下的就是只出现一次的
     */
    public static int singleNumberBest(int[] nums) {
        int number = nums[0];
        for (int i = 1; i < nums.length; i++) {
            number = number ^ nums[i];
        }
        return number;
    }

    public static void main(String[] args) {
        singleNumberBest(new int[]{4, 1, 2, 3, 3, 2, 1});
    }
}
