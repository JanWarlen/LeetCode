package com.janwarlen.ac.design;

import java.util.ArrayList;
import java.util.List;

public class MinStack {

    private List<Integer> eles;
    private int topIdx;
    private int min = Integer.MAX_VALUE;

    public MinStack() {
        eles = new ArrayList<>();
        topIdx = -1;
    }

    public void push(int val) {
        topIdx += 1;
        if (val <= min) {
            // 此处操作时减少pop时，还要重新筛选最小值的耗时
            // 当最小元素出栈，之前的最小值就是此时的栈顶元素
            eles.add(topIdx, min);
            min = val;
            topIdx += 1;
        }
        eles.add(topIdx, val);
    }

    public void pop() {
        if (eles.get(topIdx) <= min) {
            eles.remove(topIdx);
            topIdx -= 1;
            min = eles.get(topIdx);
            eles.remove(topIdx);
            topIdx -= 1;
        } else  {
            eles.remove(topIdx);
            topIdx -= 1;
        }
    }

    public int top() {
        return eles.get(topIdx);
    }

    public int getMin() {
        return min;
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(1);
        System.out.println(minStack.getMin());
        System.out.println(minStack.top());
        minStack.pop();
        System.out.println(minStack.getMin());
    }
}
