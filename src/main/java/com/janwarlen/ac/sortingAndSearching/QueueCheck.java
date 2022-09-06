package com.janwarlen.ac.sortingAndSearching;

import java.util.Arrays;
import java.util.List;

// https://www.lintcode.com/problem/334/
public class QueueCheck {
    /**
     * @param heights: Students height
     * @return: How many people are not where they should be
     */
    public int orderCheck(List<Integer> heights) {
        // write your code here
        Integer[] integers = heights.toArray(new Integer[]{});
        Arrays.sort(integers);
        int count = 0;
        for (int i = 0; i < integers.length; i++) {
            if (!integers[i].equals(heights.get(i))) {
                count++;
            }
        }

        return count;
    }
}
