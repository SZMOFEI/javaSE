package com.mofei.jvm.volatile1;

import java.util.concurrent.TimeUnit;

/**
 * @author mofei
 * @date 2021/2/27 8:03
 */
public class VolatileMain {

    public static void main(String[] args) {
        Data data = new Data();
        new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            data.addCount();
            System.out.println(Thread.currentThread().getName()+" is data = " + data.count);
        }, "AAA").start();

        while (data.count == 0) {

        }
        System.out.println("data.  = " + data.count);
    }
}

class Data {
    volatile int count = 0;

    public void addCount() {
        this.count = 60;
    }
}

