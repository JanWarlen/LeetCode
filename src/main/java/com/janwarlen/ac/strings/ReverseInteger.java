package com.janwarlen.ac.strings;

public class ReverseInteger {
    public static int reverse(int x) {
        String s = String.valueOf(x);
        int idx = 0;
        if ('-' == s.charAt(0)) {
            idx = 1;
        }
        char[] chars = s.toCharArray();
        int end = chars.length - 1;
        while (idx < end) {
            char tmp = chars[idx];
            chars[idx++] = chars[end];
            chars[end--] = tmp;
        }
        try {
            return Integer.parseInt(String.valueOf(chars));
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public int reverseClean(int x) {
        long res = 0;
        while (x != 0) {
            res *= 10;
            res += x % 10;
            x /= 10;
        }
        return (int) res == res ? (int) res : 0;
    }

    public int reverseBest(int x) {
        int ans = 0;
        while (x != 0) {
            int rem = x % 10;
            x = x / 10;
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && rem > 7))
                return 0;
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && rem < -8))
                return 0;
            ans = ans * 10 + rem;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(reverse(123));
    }
}
