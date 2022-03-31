package com.janwarlen.ac.array;

import java.util.ArrayList;
import java.util.List;

public class IntersectionOfTwoArraysII {

    public int[] intersect(int[] nums1, int[] nums2) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                if (nums1[i] == nums2[j]) {
                    res.add(nums1[i]);
                    nums1[i] = -1;
                    nums2[j] = -1;
                    break;
                }
            }
        }

        int[] ints = new int[res.size()];
        for (int i = 0; i < ints.length; i++) {
            ints[i] = res.get(i);
        }
        return ints;
    }


    /**
     * 此处的hash数组可以理解为标记数组
     * 先用nums1标记包含的数字的出现次数
     * 再遍历nums2判断之前是否标记过，同时将标记-1，避免重复计算
     */
    public int[] intersectBest(int[] nums1, int[] nums2) {
        byte [] hash = new byte[1001];
        for(int i=0; i< nums1.length; i++){
            hash[nums1[i]]++;
        }

        List<Integer> intList = new ArrayList<>();
        for(int i=0; i< nums2.length; i++){
            if(hash[nums2[i]] > 0){
                hash[nums2[i]]--;
                intList.add(nums2[i]);
            }
        }

        int [] out = new int[intList.size()];
        for(int i=0; i<out.length; i++){
            out[i] = intList.get(i);
        }
        return out;
    }

    public static void main(String[] args) {

    }
}
