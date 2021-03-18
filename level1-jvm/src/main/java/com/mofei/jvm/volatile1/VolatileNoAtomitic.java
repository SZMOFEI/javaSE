package com.mofei.jvm.volatile1;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mofei
 * @date 2021/2/27 16:49
 */
public class VolatileNoAtomitic {
    public static void main(String[] args) {
        MyData myData = new MyData();
        for (int i = 0; i < 100; i++) {
            new Thread(() -> {
//                System.out.println("Thread.currentThread() = " + Thread.currentThread());
                for (int j = 0; j < 10000; j++) {
                    myData.addCount();
                }
            }, "threadName").start();
        }
        while (Thread.activeCount() > 2) { //main方法的线程，守护线程 。 等待所有的线程都执行完以后再统计技术
            Thread.yield();
        }
        System.out.println("MyData.count = " + myData.count);
    }
}

class MyData {
    volatile AtomicInteger count = new AtomicInteger();

    public void addCount() {
        count.getAndIncrement();
    }
}
