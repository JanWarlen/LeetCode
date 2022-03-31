package com.janwarlen.ac.strings;

public class ReverseString {

    public void reverseString(char[] s) {
        int start = 0;
        int end = s.length - 1;
        while (start < end) {
            char tmp = s[start];
            s[start++] = s[end];
            s[end--] = tmp;
        }
    }

    public static void main(String[] args) {

    }
}
