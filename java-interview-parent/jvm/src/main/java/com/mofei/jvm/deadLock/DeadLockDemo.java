package com.mofei.jvm.deadLock;

/**
 *
 * @author mofei
 * @since 2021/3/8 9:33
 */
public class DeadLockDemo {
    public static void main(String[] args) {
        String lockA = "LockA";
        String lockb = "LockB";
        new Thread(new HoldLock(lockA, lockb), "ThreadAA").start();
        new Thread(new HoldLock(lockb, lockA), "ThreadBB").start();
    }
}

class HoldLock implements Runnable {
    private String lockA;
    private String lockB;

    @Override
    public void run() {
        synchronized (lockA) {
                System.out.println("线程" + Thread.currentThread().getName() + "\t 持有锁" + lockA + "，尝试获取锁"+lockB);
            synchronized (lockB) {
                System.out.println("线程" + Thread.currentThread().getName() + "\t 持有锁" + lockB + "，尝试获取锁"+lockA);
            }
        }
    }

    public HoldLock(String lockA, String lockB) {
        this.lockA = lockA;
        this.lockB = lockB;
    }
}
