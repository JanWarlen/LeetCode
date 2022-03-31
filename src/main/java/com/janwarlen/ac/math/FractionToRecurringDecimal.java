package com.janwarlen.ac.math;

import java.util.*;

// Fraction to Recurring Decimal
public class FractionToRecurringDecimal {
    public String fractionToDecimal(int numerator, int denominator) {
        if (numerator == 1 << 31 && denominator == -1) return String.valueOf(numerator).substring(1);
        int digit = numerator / denominator;
        long fractionPart = numerator % denominator;
        if (0 == fractionPart) {
            return String.valueOf(digit);
        }
        StringBuilder sb = new StringBuilder();
        int offset = 0;
        if (numerator > 0 != denominator > 0) {
            // 结果是负数
            if (0 == digit) {
                sb.append("-");
            }
        }
        sb.append(digit).append('.');
        offset += sb.length();
        // 开始进行小数部分运算
        Map<Long, Integer> remainders = new HashMap<>();
        remainders.put(fractionPart, 0);
        int subOffset = 0;
        while (0 != fractionPart) {
            subOffset++;
            fractionPart *= 10;
            int i = (int) (fractionPart / denominator);
            sb.append(Math.abs(i));
            fractionPart = fractionPart % denominator;
            if (remainders.containsKey(fractionPart)) {
                int index = remainders.get(fractionPart);
                sb.insert(index + offset, "(");
                sb.append(")");
                break;
            }
            remainders.put(fractionPart, subOffset);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        FractionToRecurringDecimal test = new FractionToRecurringDecimal();
        System.out.println(test.fractionToDecimal(1, 4));
        System.out.println(test.fractionToDecimal(1, 6));
        System.out.println(test.fractionToDecimal(1, 7)); // 0.142857142857
        System.out.println(test.fractionToDecimal(7, 12));
        System.out.println(test.fractionToDecimal(7, -12));
        System.out.println(test.fractionToDecimal(-1, -2147483648)); // 0.0000000004656612873077392578125
        System.out.println(test.fractionToDecimal(-50, 8));
        System.out.println(test.fractionToDecimal(-2147483648, -1));
    }
}
