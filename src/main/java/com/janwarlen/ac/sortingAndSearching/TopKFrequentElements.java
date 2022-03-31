package com.janwarlen.ac.sortingAndSearching;

import java.util.HashMap;
import java.util.Map;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            Integer orDefault = count.getOrDefault(num, 0);
            count.put(num, orDefault + 1);
        }
        int[] res = new int[k];
        findEles(res, count, k);
        return res;
    }

    private void findEles(int[] res, Map<Integer, Integer> count, int k) {
        if (k == 0) {
            return;
        }
        int max = 0, ele = 0;
        for (Map.Entry<Integer, Integer> entry : count.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
                ele = entry.getKey();
            }
        }
        count.remove(ele);
        res[--k] = ele;
        findEles(res, count, k);
    }
}
