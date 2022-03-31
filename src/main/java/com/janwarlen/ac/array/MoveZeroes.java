package com.janwarlen.ac.array;

public class MoveZeroes {

    public void moveZeroes(int[] nums) {
        int[] res = new int[nums.length];
        int idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (0 != nums[i]) {
                res[idx++] = nums[i];
            }
        }
        for (; idx < res.length; idx++) {
            res[idx] = 0;
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = res[i];
        }
    }

    /**
     * 和RemoveDuplicatesFromSortedArray一样的思路....
     * 空间消耗是我的一半，基本无额外消耗
     */
    public void moveZeroesBest(int[] nums) {
        int count = 0;
        for(int i = 0;i<nums.length;i++){
            if(nums[i] != 0){
                nums[count++]=nums[i];
            }
        }
        while(count<nums.length){
            nums[count++]=0;
        }
    }

    public static void main(String[] args) {

    }
}
