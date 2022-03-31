package com.janwarlen.ac.strings;

public class ValidPalindrome {

    public static boolean isPalindrome(String s) {
        char[] chars = s.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start < end) {
            if (isAlphaNumeric(chars[start])) {
                start++;
                continue;
            }
            if (isAlphaNumeric(chars[end])) {
                end--;
                continue;
            }
            if (chars[start] != chars[end] && Character.toLowerCase(chars[start]) != Character.toLowerCase(chars[end])) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    private static boolean isAlphaNumeric(char aChar) {
        return (48 > aChar || aChar > 57) && (65 > aChar || 90 < aChar) && (97 > aChar || 122 < aChar);
    }

    public static void main(String[] args) {
//        isPalindrome("A man, a plan, a canal: Panama");
        isPalindrome("0P");
    }

}
