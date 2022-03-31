package com.janwarlen.ac.math;

import java.math.BigInteger;
import java.util.ArrayList;

// Factorial Trailing Zeroes
public class FactorialTrailingZeroes {

    /**
     * 都是利用相同的规律，不过此解法更巧妙
     */
    public int trailingZeroesPro(int n) {
        int res = 0;
        while (n != 0) {
            int t = n / 5;
            res += t;
            n = t;
        }
        return res;
    }

    public int trailingZeroes(int n) {
        int res = 0;
        int fac = (int) Math.floor(Math.log(n) / Math.log(5));
        for (int i = 1; i < fac + 1; i++) {
            res += n / Math.pow(5, i);
        }
        return res;
    }

    public static synchronized BigInteger bigNumber(int num) {
        //利用BigInteger类计算阶乘
        ArrayList<BigInteger> list = new ArrayList<>();
        list.add(BigInteger.valueOf(1));
        for (int i = list.size(); i <= num; i++) {
            BigInteger lastfact = list.get(i - 1);
            BigInteger nextfact = lastfact.multiply(BigInteger.valueOf(i));
            list.add(nextfact);
        }
        return list.get(num);
    }

    public static int countTrailingZero(String number) {
        int res = 0;
        for (int i = number.length() - 1; i > -1; i--) {
            if (number.charAt(i) == '0') {
                res++;
            } else {
                return res;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 627; i++) {
            System.out.println(i + "-----------" + countTrailingZero(bigNumber(i).toString()) + "-------------" + Math.floor(Math.log(i) / Math.log(5)));
        }
    }
}
