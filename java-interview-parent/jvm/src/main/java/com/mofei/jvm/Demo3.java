package com.mofei.jvm;

import java.util.concurrent.CountDownLatch;

/**
 * @author mofei
 * @since 2021/3/4 11:57
 */
public class Demo3 {
    public static void main(String[] args) throws InterruptedException {
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
