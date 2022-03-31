package com.janwarlen.ac.array;

import java.util.HashMap;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if ((nums[i] + nums[j]) == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 1};
    }

    public int[] twoSumPro(int[] nums, int target) {
        HashMap<Integer, Integer> idxs = new HashMap();
        for (int i = 0; i < nums.length; i++) {
            if (idxs.containsKey(target - nums[i])) {
                return new int[]{i, idxs.get(target - nums[i])};
            } else {
                idxs.put(nums[i], i);
            }
        }

        return new int[]{0, 1};
    }

    public static void main(String[] args) {

    }
}
