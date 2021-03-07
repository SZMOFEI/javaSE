package com.mofei.jvm.prod_consumer;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 1.MQ模型的底层原理, 产生一个,消费一个 .
 * CAS/volatile(dcl)/atomicInteger/BlockQueue/线程交互
 * 题目:
 * 大boss喊开始  , 生产者产生一个消息 , 消费者消费一个消息  .5秒后,喊停.所有的结束
 *
 * @author mofei
 * @date 2021/3/7 14:50
 */
class MyResource {
    private volatile Boolean FLAG = true;//默认开启
    private AtomicInteger atomicInteger = new AtomicInteger();
    private BlockingQueue<String> blockingQueue;

    public MyResource(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
        System.out.println("消息队列对象初始化,对象是 = " + blockingQueue.getClass().getName());
    }

    /**
     * 生产消息
     */
    public void prod() throws InterruptedException {
        String data;
        boolean result;
        while (FLAG) {
            data = atomicInteger.incrementAndGet() + "";
            result = blockingQueue.offer(data, 2, TimeUnit.SECONDS);
            if (result) {
                System.out.println(Thread.currentThread().getName() + "\t 生产消息成功 , " + data);
            } else {
                System.out.println(Thread.currentThread().getName() + "\t 生产消息失败 , " + data);
            }
            TimeUnit.SECONDS.sleep(1);
        }
        System.out.println(Thread.currentThread().getName() + "\t 大BOSS 喊停,FLAG=false  ");
    }

    public void consumer() {
        String result;
        while (FLAG) {
            try {
                result = blockingQueue.poll(2, TimeUnit.SECONDS);
                if (null == result || result.equals("")) {
                    FLAG = false;
                    System.out.println(Thread.currentThread().getName() + "\t 消费消息超过2秒,失败 , ");
                    System.out.println();
                    System.out.println();
                    return;
                }
                System.out.println(Thread.currentThread().getName() + "\t 消费消息成功 , " + result);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop () {
        this.FLAG = false;
    }
}

public class ProdConsumer_BlockQueueDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource(new ArrayBlockingQueue<String>(3));
        new Thread(() -> {
            try {
                System.out.println("生产现场开始启动 = " + Thread.currentThread());
                myResource.prod();
                System.out.println();
                System.out.println();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "Prod").start();
        new Thread(() -> {
            try {
                System.out.println("消费线程 开始启动 = " + Thread.currentThread());
                System.out.println();
                System.out.println();
                myResource.consumer();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "Consumer").start();

        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        myResource.stop();
        System.out.println("大老板叫停了");
        System.out.println();
        System.out.println();
    }
}
