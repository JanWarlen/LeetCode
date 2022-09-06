package com.janwarlen.ac.design;

// https://www.lintcode.com/problem/3364
public class PrintIncrementingNumbers implements Runnable {

    private int threadId;

    private Object o;

    public static volatile int res = 1;

    // You can add other member variables needed.
    // -- write your code here --

    public PrintIncrementingNumbers(int threadId, Object o) {
        this.threadId = threadId;
        this.o = o;
    }

    public void run1() {
        // -- write your code here --
        while (res <= 28) {
            if (((res % 15) / 5) != (threadId - 1)) {
                Thread.yield();
                continue;
            }
            synchronized (o) {
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread " + threadId + ": " + res++);
                    if (res > 28) {
                        return;
                    }
                }
            }
            Thread.yield();
        }
    }

    @Override
    public void run() {
        // -- write your code here --
        while (res <= 28) {
            synchronized (o) {
                if (((res % 15) / 5) != (threadId - 1)) {
                    try {
                        o.wait();
                        continue;
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
                for (int i = 0; i < 5; i++) {
                    System.out.println("Thread " + threadId + ": " + res++);
                    if (res > 28) {
                        o.notifyAll();
                        return;
                    }
                }
                o.notifyAll();
            }
        }
    }

    /**
     * 耗时最少
     */
    public void official() {
        while (res < 28) {
            // 获取自身锁
            synchronized (o) {
                // 判断线程是否对应
                if (res / 5 % 3 + 1 == threadId) {
                    // 线程对应 进行递增数字的打印操作
                    for (int i = 0; i < 5; i++) {
                        if (res < 28)
                            // 这里的 ++printNum 可以放在语句里面也可以放在外面进行自递增
                            System.out.println("Thread " + threadId + ": " + (res++));
                        else
                            break;
                    }
                    // 打印完毕 通知其他等待状态中的线程 并释放自身锁
                    o.notifyAll();
                } else {
                    // 线程不对应 进入等待状态
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Object o = new Object();
        try {
            new Thread(new PrintIncrementingNumbers(1, o)).start();
            new Thread(new PrintIncrementingNumbers(2, o)).start();
            new Thread(new PrintIncrementingNumbers(3, o)).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
