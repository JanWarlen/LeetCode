package com.janwarlen.ac.math;

import java.util.*;

// Excel Sheet Column Number
public class ExcelSheetColumnNumber {

    // 1 ms
    public int titleToNumberPro(String columnTitle) {
        int ret = 0;
        for (int i = 0; i < columnTitle.length(); i++) {
            char c = columnTitle.charAt(i);
            ret *= 26;
            ret += (c - 'A' + 1);
        }
        return ret;
    }

    private static final List<Integer> num =
            new ArrayList<>(Arrays.asList(1, 26, 676, 17576, 456976, 11881376, 308915776));

    // 3 ms
    public int titleToNumber(String columnTitle) {
        char[] chars = columnTitle.toCharArray();
        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            res += (((int) chars[i]) - 64) * num.get(chars.length - i - 1);
        }
        return res;
    }

    public static void main(String[] args) {
        ExcelSheetColumnNumber test = new ExcelSheetColumnNumber();
        // 2147483647
        System.out.println(test.titleToNumber(("FXSHRXW")));
    }
}
