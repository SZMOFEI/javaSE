package com.mofei.jvm.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * AA 打印五次
 * BB 打印10次
 * CC 打印15次
 * 来十轮
 * 线程  操纵 资源类
 * 判断  干活  通知唤醒
 * 1.声明多个condition ,可以精确通知
 *
 * @author mofei
 * @date 2021/3/7 13:59
 */
class ShareDat {
    private int number = 1; // A线程是1  , B是线程 2, C是线程3

    private ReentrantLock lock = new ReentrantLock();
    Condition c1 = lock.newCondition();
    Condition c2 = lock.newCondition();
    Condition c3 = lock.newCondition();

    public void print5() {
        lock.lock();
        try {
            while (number != 1) {
                c1.await();
            }
            for (int i = 1; i <= 5; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //把条件切换, 然后通知第2个线程
            number = 2;
            c2.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }


    public void print10() {
        lock.lock();
        try {
            while (number != 2) {
                c2.await();
            }
            for (int i = 1; i <= 10; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //把条件切换, 然后通知第2个线程
            number = 3;
            c3.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void print15() {
        lock.lock();
        try {
            while (number != 3) {
                c3.await();
            }
            for (int i = 1; i <= 15; i++) {
                System.out.println(Thread.currentThread().getName() + "\t" + i);
            }
            //把条件切换, 然后通知第2个线程
            number = 1;
            c1.signal();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

public class MuiltConditionDemo {
    /**
     *  3个线程, 每个线程做多少个事情 . 1个线程做5个    2个线程做10   3个线程做 15下
     * @param args
     */
    public static void main(String[] args) {
        ShareDat shareDat = new ShareDat();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareDat.print5();
            }
        }, "AA").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareDat.print10();
            }
        }, "BB").start();
        new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                shareDat.print15();
            }
        }, "CC").start();
    }
}
