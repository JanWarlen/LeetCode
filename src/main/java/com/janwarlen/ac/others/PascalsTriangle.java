package com.janwarlen.ac.others;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangle {
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> first = new ArrayList<>();
        first.add(1);
        res.add(first);
        if (1 == numRows) {
            return res;
        }
        for (int i = 1; i < numRows; i++) {
            List<Integer> last = res.get(i - 1);
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int j = 1; j < i; j++) {
                row.add(last.get(j - 1) + last.get(j));
            }
            row.add(1);
            res.add(row);
        }
        return res;
    }

    public static void main(String[] args) {
        generate(5);
    }
}
