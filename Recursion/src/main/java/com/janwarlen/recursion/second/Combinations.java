package com.janwarlen.recursion.second;

import java.util.ArrayList;
import java.util.List;

public class Combinations {

    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i <= n - k; i++) {
            List<Integer> ints = new ArrayList<>(k);
            ints.add(i + 1);
            calculate(res, ints, i + 1, n, k - 1);
        }
        return res;
    }

    private static void calculate(List<List<Integer>> res, List<Integer> ints, int i, int n, int k) {
        if (0 == k) {
            ArrayList<Integer> integers = new ArrayList<>(ints);
            res.add(integers);
        } else {
            for (int j = 1; i + j <= n; j++) {
                ints.add(i + j);
                calculate(res, ints, i + j, n, k - 1);
                ints.remove(ints.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        combine(4, 3).stream().forEach(item -> {
            item.stream().forEach(ele -> System.out.println(ele));
            System.out.println("-------------");
        });
    }
}
