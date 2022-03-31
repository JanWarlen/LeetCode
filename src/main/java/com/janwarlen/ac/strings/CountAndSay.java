package com.janwarlen.ac.strings;

public class CountAndSay {

    public static String countAndSay(int n) {
        if (1 == n) {
            return "1";
        }
        String last = countAndSay(n - 1);
        StringBuilder sb = new StringBuilder();
        char[] chars = last.toCharArray();
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (chars[i] == chars[i - 1]) {
                count++;
            } else {
                sb.append(count).append(chars[i - 1]);
                count = 1;
            }
        }
        sb.append(count).append(chars[chars.length - 1]);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(countAndSay(4));
    }
}
