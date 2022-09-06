package com.janwarlen.ac.lambda;

import java.util.Arrays;
import java.util.Comparator;

// https://www.lintcode.com/problem/3368
public class SortByLength {
    public void sortByLength(String[] strArr) {

        // --- Write your code here ---
        Arrays.sort(strArr, Comparator.comparingInt(String::length));
        Arrays.stream(strArr).forEach(System.out::println);
    }

    public static void main(String[] args) {
        SortByLength sortByLength = new SortByLength();
        String[] strs = {"he", "see", "a", "ball"};
        sortByLength.sortByLength(strs);
    }
}
