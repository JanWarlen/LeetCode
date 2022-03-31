package com.janwarlen.ac.sortingAndSearching;

public class SearchInRotatedSortedArray {

    public int searchPro(int[] nums, int target) {
        int L = 0, R = nums.length - 1;
        while (L <= R) {
            int m = L + (R - L) / 2;
            if (nums[m] == target) {
                return m;
            } else if (nums[m] < nums[R]) {
                if (nums[m] < target && target <= nums[R]) {
                    L = m + 1;
                } else {
                    R = m - 1;
                }
            } else {
                if (nums[m] > target && target >= nums[L]) {
                    R = m - 1;
                } else {
                    L = m + 1;
                }
            }
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                return i;
            }
        }
        return -1;
    }
}
