package com.janwarlen.ac.strings;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        char[] chars = strs[0].toCharArray();
        for (int i = 0; i < chars.length; i++) {
            boolean flag = true;
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != chars[i]) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                sb.append(chars[i]);
            } else {
                return sb.toString();
            }
        }
        return sb.toString();
    }

    /**
     * 1. 优化了额外的 StringBuilder 空间使用
     * 2. 优化了`strs[0].toCharArray()` 耗时
     * 3. 因不再使用`StringBuilder`，因此append和toString的时间也优化了
     */
    public String longestCommonPrefixPro(String[] strs) {
        if (null == strs) {
            return "";
        }
        int length = strs[0].length();
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }
}
