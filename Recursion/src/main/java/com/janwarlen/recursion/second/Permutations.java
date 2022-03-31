package com.janwarlen.recursion.second;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given an array nums of distinct integers, return all the possible permutations. You can return the answer in any order.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3]
 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 * <p>
 * Example 2:
 * <p>
 * Input: nums = [0,1]
 * Output: [[0,1],[1,0]]
 * <p>
 * Example 3:
 * <p>
 * Input: nums = [1]
 * Output: [[1]]
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * All the integers of nums are unique.
 */
public class Permutations {

    public List<List<Integer>> permuteBacktracking(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int length = nums.length;
        int[] ints = new int[length];
        for (int i = 0; i < length; i++) {
            ints[i] = -1;
        }
        solution(res, nums, ints, 0, new ArrayList<>());
        return res;
    }

    private void solution(List<List<Integer>> res, int[] nums, int[] idxs, int idx, List<Integer> comb) {
        if (idx == nums.length) {
            // 已完整拼装一个组合
            res.add(new ArrayList<>(comb));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (idxs[i] != -1) {
                // 之前的组合已使用，跳过
                continue;
            } else {
                // 取出放入组合中
                comb.add(nums[i]);
                idxs[i] = 0;
                // 递归
                solution(res, nums, idxs, idx + 1, comb);
                // 撤回之前放入元素
                comb.remove(idx);
                idxs[i] = -1;
            }
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> all = new ArrayList<>();
        //从下标 0 开始的所有组合
        upset(nums, 0, all);
        return all;
    }

    /**
     * 依旧是回溯，不同的是，组合方式使用的是元素呼唤位置
     * 优化了额外保存的索引，并且也优化了临时保存组合的对象，同时优化了该对象在回溯时的恢复运算
     */
    private void upset(int[] nums, int begin, List<List<Integer>> all) {
        if (begin == nums.length) {
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int i = 0; i < nums.length; i++) {
                temp.add(nums[i]);
            }
            all.add(new ArrayList<Integer>(temp));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            swap(nums, i, begin);
            upset(nums, begin + 1, all);
            swap(nums, i, begin);
        }

    }

    private void swap(int[] nums, int i, int begin) {
        int temp = nums[i];
        nums[i] = nums[begin];
        nums[begin] = temp;
    }

    public static void main(String[] args) {

    }
}
