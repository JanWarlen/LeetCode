package com.janwarlen.ac.sortingAndSearching;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

public class SortAnArray {

    /**
     * Time Limit Exceeded
     * O(n^2)
     */
    public int[] sortArrayBubbleSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        boolean flag = false;
        for (int i = 0; i < nums.length && !flag; i++) {
            flag = true;
            for (int j = 0; j < nums.length - i; j++) {
                if (nums[j + 1] < nums[j]) {
                    swap(nums, j, j + 1);
                    flag = false;
                }
            }
        }
        return nums;
    }

    /**
     * O(n^2)
     */
    public static int[] sortArraySelectionSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        for (int i = 0; i < nums.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[minIndex] > nums[j]) {
                    minIndex = j;
                }
            }
            swap(nums, minIndex, i);
        }
        return nums;
    }

    /**
     * O(n^2)
     */
    public static int[] sortArrayInsertionSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        for (int i = 1; i < nums.length; i++) {
            // 记录，防丢
            int tmp = nums[i];
            // 往前找到比当前节点小的
            int j = i;
            while (j > 0 && nums[j - 1] > tmp) {
                nums[j] = nums[j - 1];
                j--;
            }
            if (j != i) {
                nums[j] = tmp;
            }
        }
        return nums;
    }

    /**
     * O(n^(1.3-2))
     */
    public static int[] sortArrayShellSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        // 定义 gap
        for (int gap = nums.length / 2; gap > 0; gap /= 2) {
            // 基于gap的插入排序
            for (int i = gap; i < nums.length; i++) {
                int tmp = nums[i];
                int j = i;
                while (j >= gap && nums[j - gap] > tmp) {
                    nums[j] = nums[j - gap];
                    j -= gap;
                }
                if (j != i) {
                    nums[j] = tmp;
                }
            }
        }
        return nums;
    }

    /**
     * O(NlogN)
     */
    public static int[] sortArrayMergeSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        int middle = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, middle);
        int[] right = Arrays.copyOfRange(nums, middle, nums.length);
        return mergeSort(sortArrayMergeSort(left), sortArrayMergeSort(right));
    }

    public static int[] mergeSort(int[] left, int[] right) {
        int[] newarr = new int[left.length + right.length];
        int i = 0, j = 0, x = 0;
        while (i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                newarr[x++] = left[i++];
            } else {
                newarr[x++] = right[j++];
            }
        }
        while (i < left.length) {
            newarr[x++] = left[i++];
        }
        while (j < right.length) {
            newarr[x++] = right[j++];
        }
        return newarr;
    }

    public static int[] sortArrayQuickSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    public static void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        // 基准
        // 不采用随机抽取基准，会超时
        int pivot = (int) Math.floor(Math.random() * (right - left)) + left;
        swap(nums, right, pivot);
        // 大小区块划分
        int i = left;
        for (int j = left; j < right; j++) {
            if (nums[j] < nums[right]) {
                swap(nums, j, i);
                i++;
            }
        }
        // i 是从左往右第一个大于基准
        swap(nums, right, i);
        quickSort(nums, left, i - 1);
        quickSort(nums, i + 1, right);
    }

    private static void swap(int[] nums, int i, int j) {
        int tmp = nums[j];
        nums[j] = nums[i];
        nums[i] = tmp;
    }

    /**
     * O(NlogN)
     */
    public static int[] sortArrayHeapSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        int length = nums.length;
        // 构建大顶堆
        buildMaxHeap(nums, length);
        for (int i = length - 1; i > 0; i--) {
            // 最大值转移到堆尾
            swap(nums, 0, i);
            // 堆尾移出操作范围
            length--;
            // 堆顶数据变更，需要重新维护大顶堆
            heapify(nums, 0, length);
        }
        return nums;
    }

    private static void buildMaxHeap(int[] nums, int length) {
        // 对于完全二叉树，第一个叶子节点索引值是 len/2，数组索引从0开始，不用额外+1
        // 因此 第一个非叶子节点就是 len/2 - 1
        // 构建大顶堆or小顶堆 从下而上的遍历是最方便的
        for (int i = (int) Math.floor(length / 2) - 1; i >= 0; i--) {
            heapify(nums, i, length);
        }
    }

    /**
     * 该函数用来比较当前节点和其子节点的大小，并进行交换
     *
     * @param i 当前节点索引
     */
    private static void heapify(int[] nums, int i, int length) {
        // 左子节点索引
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        // 和左子节点对比，选大值
        if (left < length && nums[left] > nums[max]) {
            max = left;
        }
        // 和右子节点对比，选大值
        if (right < length && nums[right] > nums[max]) {
            max = right;
        }
        if (max != i) {
            // 子节点比当前节点大
            // 交换
            swap(nums, i, max);
            // 子节点数据变更，递归将底层大顶堆的规则维护
            heapify(nums, max, length);
        }
    }

    /**
     * O(n+k)
     */
    public static int[] sortArrayCountingSort(int[] nums) {
        if (null == nums || nums.length < 2) {
            return nums;
        }
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
            if (nums[i] < min) {
                min = nums[i];
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int index = 0;
        for (int i = min; i <= max; i++) {
            Integer orDefault = map.getOrDefault(i, 0);
            if (orDefault != 0) {
                for (int j = 0; j < orDefault; j++) {
                    nums[index++] = i;
                }
            }
        }
        return nums;
    }

    /**
     * O(n+k)
     */
    public static int[] sortArrayRadisSort(int[] nums) {
        // 找到最大值，确定轮次
        int max = nums[0];
        for (int value : nums) {
            if (max < Math.abs(value)) {
                max = value;
            }
        }
        // 确定将执行的轮次
        int lenght = 0;
        if (max == 0) {
            lenght = 1;
        } else {
            for (long temp = max; temp != 0; temp /= 10) {
                lenght++;
            }
        }
        // 取余，留下需要计算排序部分
        int mod = 10;
        // 当前计算的位
        int dev = 1;
        // 分20是为了兼容负数，0-9是负数
        LinkedList<Integer>[] linkedLists = new LinkedList[20];
        for (int i = 0; i < linkedLists.length; i++) {
            linkedLists[i] = new LinkedList<>();
        }
        for (int i = 0; i < lenght; i++, dev *= 10, mod *= 10) {
            for (int j = 0; j < nums.length; j++) {
                // 确定当前数是哪个桶
                int index = (nums[j] % mod) / dev + 10;
                linkedLists[index].add(nums[j]);
            }
            int pos = 0;
            for (LinkedList<Integer> linkedList : linkedLists) {
                for (Integer integer : linkedList) {
                    nums[pos++] = integer;
                }
            }
            // 清空，避免脏数据
            for (LinkedList<Integer> linkedList : linkedLists) {
                linkedList.clear();
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 3, 1, -2, -10};
        System.out.println(Arrays.toString(sortArrayRadisSort(nums)));
        // 最快，实际场景不用考虑自己实现排序
        Arrays.sort(nums);
    }
}
