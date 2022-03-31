package com.janwarlen.ac.array;

import java.util.Arrays;

public class ContainsDuplicate {

    public boolean containsDuplicateSort(int[] nums) {
        if (null == nums) {
            return false;
        }
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
