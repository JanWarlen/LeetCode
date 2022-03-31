package com.janwarlen.ac.sortingAndSearching;

public class SearchForARange {
    public int[] searchRange(int[] nums, int target) {
        int[] res = {-1, -1};
        boolean start = true;
        for (int i = 0; i < nums.length; i++) {
            if (start && target == nums[i]) {
                start = false;
                res[0] = i;
            }
            if (!start && target != nums[i]) {
                res[1] = i - 1;
                break;
            }
        }
        if (res[0] > res[1]) {
            res[1] = nums.length - 1;
        }
        return res;
    }
}
