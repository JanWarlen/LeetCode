package com.janwarlen.ac.others;

// Sum of Two Integers
public class SumOfTwoIntegers {
    // without using the operators + and -.
    public int getSum(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            b = (a & b) << 1;
            a = sum;
        }
        return a;
    }

    public static void main(String[] args) {
        SumOfTwoIntegers test = new SumOfTwoIntegers();
        System.out.println(test.getSum(5, 5));
        System.out.println(test.getSum(20, 30));
    }
}
