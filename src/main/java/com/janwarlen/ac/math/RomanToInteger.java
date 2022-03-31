package com.janwarlen.ac.math;

import java.util.HashMap;
import java.util.Map;

/**
 * Symbol       Value
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 */
public class RomanToInteger {
    public static int romanToInt(String s) {
        int res = 0;
        Map<Character, Integer> dic = new HashMap<>();
        dic.put('I', 1);
        dic.put('V', 5);
        dic.put('X', 10);
        dic.put('L', 50);
        dic.put('C', 100);
        dic.put('D', 500);
        dic.put('M', 1000);
        char[] chars = s.toCharArray();
        int i = 0;
        while (i < chars.length - 1) {
            if (dic.get(chars[i + 1]) <= dic.get(chars[i])) {
                res += dic.get(chars[i]);
            } else {
                res += dic.get(chars[i + 1]) - dic.get(chars[i]);
                i++;
            }
            i++;
        }
        if (i == chars.length - 1) {
            res += dic.get(chars[i]);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(romanToInt("LVIII"));
        System.out.println(romanToInt("MCMXCIV"));
    }
}

