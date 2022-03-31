package com.janwarlen.ac.sortingAndSearching;

public class SortColors {
    public void sortColors(int[] nums) {
        int red = 0, white = 0, blue = 0;
        for (int num : nums) {
            if (0 == num) {
                red++;
            } else if (1 == num) {
                white++;
            } else {
                blue++;
            }
        }
        for (int i = 0; i < red; i++) {
            nums[i] = 0;
        }
        for (int i = red; i < red + white; i++) {
            nums[i] = 1;
        }
        for (int i = red + white; i < nums.length; i++) {
            nums[i] = 2;
        }
    }
}
