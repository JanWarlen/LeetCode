package com.janwarlen.ac.design;

import java.util.*;

// Insert Delete GetRandom O(1)
public class InsertDeleteGetRandomO1 {
    static class RandomizedSet {

        int size = 0;
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> mem = new HashMap<>();
        private final Random rd = new Random();

        public RandomizedSet() {

        }

        /**
         * Inserts an item val into the set if not present.
         * Returns true if the item was not present, false otherwise.
         */
        public boolean insert(int val) {
            if (null != mem.get(val)) {
                return false;
            }
            list.add(val);
            mem.put(val, size++);
            return true;
        }

        /**
         * Removes an item val from the set if present.
         * Returns true if the item was present, false otherwise.
         */
        public boolean remove(int val) {
            Integer index = mem.get(val);
            if (null != index) {
                if ((size - 1) != index) {
                    // 和结尾元素替换，确保index记录不会紊乱
                    Integer ele = list.get(size - 1);
                    list.set(index, ele);
                    mem.put(ele, index);
                    index = size - 1;
                }
                list.remove(index.intValue());
                mem.remove(val);
                size--;
                return true;
            }
            return false;
        }

        /**
         * Returns a random element from the current set of elements
         * (it's guaranteed that at least one element exists when this method is called).
         * Each element must have the same probability of being returned.
         */
        public int getRandom() {
            return list.get(rd.nextInt(size));
        }
    }

    public static void main(String[] args) {
        RandomizedSet randomizedSet = new RandomizedSet();
        randomizedSet.insert(0);
        randomizedSet.insert(1);
        randomizedSet.remove(0);
        randomizedSet.insert(2);
        randomizedSet.remove(1);
        System.out.println(randomizedSet.getRandom());
    }
}
