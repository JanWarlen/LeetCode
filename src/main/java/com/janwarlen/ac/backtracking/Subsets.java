package com.janwarlen.ac.backtracking;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        add(res, new ArrayList<>(), nums, 0);
        return res;
    }

    private static void add(List<List<Integer>> res, List<Integer> solution, int[] nums, int i) {
        if (nums.length == i) {
            ArrayList<Integer> tmp = new ArrayList<>(solution);
            res.add(tmp);
            return;
        }
        // 添加
        solution.add(nums[i]);
        add(res, solution, nums, i + 1);
        // 不添加
        solution.remove(solution.size() - 1);
        add(res, solution, nums, i + 1);
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3};

        subsets(test);
    }
}
