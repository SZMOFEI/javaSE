package com.mofei.jvm.deadLock;

/**
 * @author mofei
 * @since 2021/3/8 10:03
 */
public class DeadLockDemo2 {
    public static void main(String[] args) {
        String lockA = "lockA";
        String lockB = "lockB";
        new Thread(new DeadLockHold(lockA,lockB)).start();
        new Thread(new DeadLockHold(lockB,lockA)).start();
    }
}
