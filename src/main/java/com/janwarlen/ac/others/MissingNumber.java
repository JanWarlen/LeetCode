package com.janwarlen.ac.others;

import java.util.Arrays;

public class MissingNumber {
    public int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i) {
                return i;
            }
        }
        return 0;
    }

    public int missingNumberBest(int[] nums) {
        if (nums == null || nums.length <= 0) return 0;

        int actualSum = 0;
        for (int i = 1; i <= nums.length; i++) {
            actualSum += i;
        }

        for (int i = 0; i < nums.length; i++) {
            actualSum -= nums[i];
        }

        return actualSum;
    }
}
