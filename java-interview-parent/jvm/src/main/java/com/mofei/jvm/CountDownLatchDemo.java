package com.mofei.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * @author mofei
 * @since 2021/3/4 11:57
 */
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(800);
//        method1();
        for (int i = 0; i < 800; i++) {
            final int finalI = i;
            new Thread(() -> {
                System.out.println("第 " + finalI + " 个人离开公司= ");
                countDownLatch.countDown();
            }, i + "").start();
        }
        countDownLatch.await();
        System.out.println("组长离开公司，关门。。。。。。。。。。。。");
    }


    private static void method1() throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 1; i < 11; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println("第 " + finalI + " 个人离开公司= ");
                countDownLatch.countDown();
            }, i + "").start();
        }
        countDownLatch.await();
        System.out.println("组长离开公司，关门。。。。。。。。。。。。");
    }
}
