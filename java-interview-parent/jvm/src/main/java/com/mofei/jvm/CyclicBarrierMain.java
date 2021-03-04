package com.mofei.jvm;

/**
 * 银行有季度数据统计，3个线程统计每个线程的数量
 * cyclicBarrier 就是循环栅栏的意思
 *
 * @author mofei
 * @since 2021/3/4 14:08
 */
public class CyclicBarrierMain {

    public static void main(String[] args) {
        CyclicBarrierThread barrierThread = new CyclicBarrierThread();
        barrierThread.count();
    }
}

