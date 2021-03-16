package com.mofei.jvm.deadLock;

/**
 * @author mofei
 * @since 2021/3/8 10:04
 */
public class DeadLockHold implements Runnable {
    String lockA, lockB;

    public DeadLockHold(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }

    @Override
    public void run() {
        synchronized (lockA) {
            System.out.println(Thread.currentThread().getName() + "线程持有锁" + lockA + ",尝试获取锁" + lockB);
            synchronized (lockB) {
                System.out.println(Thread.currentThread().getName() + "线程持有锁" + lockB + ",尝试获取锁" + lockA);
            }
        }
    }
}
