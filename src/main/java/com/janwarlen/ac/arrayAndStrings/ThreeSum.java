package com.janwarlen.ac.arrayAndStrings;

import java.util.*;

public class ThreeSum {

    /**
     * 先排序，方便略过重复元素，减少比对时间
     * 通过改变求三元素和为0  为 计算首位相加为-某值，直接免去了比对去重环节
     */
    public List<List<Integer>> threeSumPro(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i = 0; i < nums.length-2; i++) {
            if (i == 0 || nums[i] != nums[i - 1]) {
                int lo = i+1, hi = nums.length-1, sum = -nums[i];
                while (lo < hi) {
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        lo++; hi--;
                    } else if (nums[lo] + nums[hi] < sum) {
                        // 略过重复
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    } else {
                        // 略过重复
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    /**
     * 简单暴力思路
     * 顺序遍历，找到符合要求的三个元素
     * 和已经保存的元素集合对比是否重复，未重复则添加到结果集中
     * 超时，主要耗时在于判断是否重复
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (0 == (nums[i] + nums[j] + nums[k])) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
                        tmp.add(nums[k]);
                        if (check(res, tmp)) {
                            res.add(tmp);
                        }
                    }
                }
            }
        }
        return res;
    }

    private static boolean check(List<List<Integer>> res, List<Integer> tmp) {
        for (List<Integer> re : res) {
            if (compare(re, tmp)) {
                return false;
            }
        }
        return true;
    }

    private static boolean compare(List<Integer> re, List<Integer> tmp) {
        int[] ints = {0, 0, 0};
        for (Integer ele : re) {
            for (int i = 0; i < 3; i++) {
                if (ints[i] > -1 && ele.equals(tmp.get(i))) {
                    ints[i] = -1;
                    break;
                }
            }
        }
        return (ints[0] + ints[1] + ints[2]) == -3;
    }

    public static void main(String[] args) {
        List<List<Integer>> lists = threeSum(new int[]{-1, 0, 1, 2, -1, -4});
        print(lists);
        lists = threeSum(new int[]{0, 0, 0, 0});
        print(lists);
        lists = threeSum(new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0});
        print(lists);
    }

    private static void print(List<List<Integer>> lists) {
        System.out.println("--------------------------------");
        for (int i = 0; i < lists.size(); i++) {
            List<Integer> integers = lists.get(i);
            for (int j = 0; j < integers.size(); j++) {
                System.out.print(integers.get(j) + ",");
            }
            System.out.print("|");
        }
        System.out.println(" ");
    }
}
