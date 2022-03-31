package com.janwarlen.ac.arrayAndStrings;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    /**
     * 通过数组标记替代set判断当前元素是否重复，优化运行时间
     */
    public static int lengthOfLongestSubstringPro(String s) {
        int res = 0, path = 0;
        // English letters, digits, symbols and spaces
        // ASCII 最大编码 127
        int[] records = new int[128];
        for (int i = 0; i < s.length(); i++) {
            records[s.charAt(i)]++;
            while (records[s.charAt(i)] > 1 && path > 0) {
                records[s.charAt(i - path--)]--;
            }
            path++;
            res = Math.max(res, path);
        }
        return res;
    }

    public static int lengthOfLongestSubstring(String s) {
        if (null == s || "".equals(s)) {
            return 0;
        }
        int res = 1, maxLength = 0;
        char[] chars = s.toCharArray();
        Set<Character> sub = new HashSet<>();
        for (int i = 0; i < chars.length; i++) {
            sub.add(chars[i]);
            maxLength += 1;
            if (sub.size() < maxLength) {
                sub.clear();
                res = Math.max(res, maxLength - 1);
                i = i - maxLength + 1;
                maxLength = 0;
            }
        }
        res = Math.max(res, sub.size());
        return res;
    }

    public static void main(String[] args) {
//        String text = "pwwkew";
//        String text = "dvdf";
//        String text = "abcabcbb";
//        String text = "au";
        String text = "ibaz";
        System.out.println(lengthOfLongestSubstringPro(text));
    }
}
