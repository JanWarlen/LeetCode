package com.janwarlen.ac.strings;

public class FirstUniqueCharacterInAString {

    public int firstUniqChar(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (s.indexOf(chars[i]) == s.lastIndexOf(chars[i])) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 条件限制s只包含小写字母
     */
    public int firstUniqCharBest(String s) {
        int max = Integer.MAX_VALUE;

        for (char ch = 'a'; ch <= 'z'; ch++) {
            int tmp = s.indexOf(ch);
            if (tmp != -1 && tmp == s.lastIndexOf(ch))
                // 此处不直接return索引是因为根据字母表遍历判断，当前并不是第一个uniq元素
                max = Math.min(max, tmp);
        }

        return max == Integer.MAX_VALUE ? -1 : max;
    }

    public static void main(String[] args) {

    }
}
