package com.mofei.jvm.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * 同步队列不存取消息, 生产一个,消费一个, 否则不能存储进去
 * @author mofei
 * @date 2021/3/6 14:29
 */
public class SynchronosQueueDemo {
    public static void main(String[] args) {
        BlockingQueue<String> blockingQueue = new SynchronousQueue();
        new Thread(() -> {
            //1.生产4个消息
            try {
                System.out.println(Thread.currentThread().getName() + "\t put 1");
                blockingQueue.put("1");
                //如果不消费, 插不进入
                System.out.println(Thread.currentThread().getName() + "\t put 2");
                blockingQueue.put("2");
                System.out.println(Thread.currentThread().getName() + "\t put 3");
                blockingQueue.put("3");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
                Thread.sleep(5000);
                System.out.println(blockingQueue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
