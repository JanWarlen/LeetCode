package com.janwarlen.ac.strings;

import java.util.HashMap;
import java.util.Map;

public class ValidAnagram {

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        Map<Character, Integer> count = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Integer orDefault = count.getOrDefault(chars[i], 0);
            count.put(chars[i], orDefault + 1);
        }
        chars = t.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            Integer orDefault = count.getOrDefault(chars[i], 0);
            if (0 == orDefault) {
                return false;
            }
            count.put(chars[i], orDefault - 1);
        }
        return true;
    }

    /**
     * 同样的思路，不过利用全都是小写字母的限制
     * 将标记存储替换为数组，因char可等替换为int，因此可以更快捷的进行标记位值的变更
     */
    public boolean isAnagramPro(String s, String t) {
        char[] arr1 = s.toCharArray();
        char[] arr2 = t.toCharArray();
        int[] alpha = new int[26];

        if (arr1.length == arr2.length) {
            for (int i = 0; i < arr1.length; i++) {
                alpha[arr1[i] - 'a']++;
            }
            for (int i = 0; i < arr2.length; i++) {
                alpha[arr2[i] - 'a']--;
            }
            for (int i = 0; i < alpha.length; i++) {
                if (alpha[i] != 0) {
                    return false;
                }
            }
        } else {
            return false;
        }
        return true;
    }


}
