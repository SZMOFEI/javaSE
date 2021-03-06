package com.mofei.jvm.prod_consumer;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author mofei
 * @date 2021/3/6 14:45
 * 线程         操作              资源类
 * 判断         干活              通知
 * 防止虚假唤醒机制  (多线程需要使用while进行判断)
 */

class ShareData {
    private int number = 0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    //增加
    public void increment() throws InterruptedException {
        lock.lock();
        try {
            while (number != 0) {
                //等待,不能生产
                condition.await();
            }
            //2加货
            number++;
            System.out.println("线程"+Thread.currentThread().getName() + "\t 增加了数值" + number);
            //3. 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    //减少
    public void decrement() {
        lock.lock();
        try {
            while (number == 0) {
                //等待,不能生产
                condition.await();
            }
            //2加货
            number--;
            System.out.println("线程"+Thread.currentThread().getName() + "\t" + number);
            //3. 通知唤醒
            condition.signalAll();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class ProdConsumer_TraditionDemo {
    public static void main(String[] args) {
        ShareData shareData = new ShareData();
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },   "AA").start();
            new Thread(() -> {
                shareData.decrement();
            }, "BB").start();
            new Thread(() -> {
                try {
                    shareData.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },   "AA").start();
            new Thread(() -> {
                shareData.decrement();
            }, "BB").start();
        }

    }
}
