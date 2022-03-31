package com.janwarlen.ac.strings;

public class StringToIntegerAtoi {
    public static int myAtoi(String s) {
        if (null == s || 0 == s.length()) {
            return 0;
        }
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean isNegative = false;
        boolean read = false;
        for (int i = 0; i < chars.length; i++) {
            if (!read && ' ' == chars[i]) {
                continue;
            } else if (!read && ('-' == chars[i] || '+' == chars[i])) {
                read = true;
                isNegative = '-' == chars[i];
            } else if ('0' <= chars[i] && '9' >= chars[i]) {
                read = true;
                sb.append(chars[i]);
            } else {
                break;
            }
        }

        int start = 0;
        for (int i = 0; i < sb.length(); i++) {
            if ('0' == sb.charAt(i)) {
                start = i + 1;
            } else {
                break;
            }
        }
        String res = sb.substring(start);
        if (res.length() == 0) {
            return 0;
        }
        if (res.length() > 10) {
            return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        }
        Long aLong = Long.valueOf(res);
        if (aLong > Integer.MAX_VALUE && !isNegative) {
            return Integer.MAX_VALUE;
        }
        if (aLong > Integer.MAX_VALUE && isNegative) {
            return Integer.MIN_VALUE;
        }
        return isNegative ? -aLong.intValue() : aLong.intValue();
    }

    public int myAtoiBest(String s) {
        if (s.length() == 0) {
            return 0;
        }

        int i = 0;
        long result = 0;
        int flag = 1;   //记录正负
        while (i < s.length() && s.charAt(i) == ' ') {
            i++;
        }
        // s = "     "
        if (i == s.length()) {
            return 0;
        }
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            flag = s.charAt(i) == '+' ? 1 : -1;
            i++;
        }
        while (i < s.length() && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            result = result * 10 + (s.charAt(i) - '0');
            if (result > Integer.MAX_VALUE) {
                return flag == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            i++;
        }

        return (int) (result) * flag;
    }

    public static void main(String[] args) {
//        myAtoi("  0000000000012345678");
        myAtoi("00000-42a1234");
    }
}
