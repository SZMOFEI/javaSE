package com.mofei.jvm.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 多线程四种方式实现 :a 继承   b 实现   c Callable  d 池化技术
 * 1.为什么使用线程池 ,优势  2.线程池如何使用  3.线程池的几个重要参数 4. 线程池的工作原理
 * //提前,线程池的底层实现就是使用了阻塞队列
 * <p>
 * /Array    ===> Arrays
 * /Collection  ==>Collections
 * /Executor   ===>  Executors
 * 线程池三种
 * a 固定
 * b 一个
 * c 多个
 *
 * @author mofei
 * @date 2021/3/7 15:58
 */
public class ThreadPoolDemo1 {
    public static void main(String[] args) {
//        System.out.println("Runtime.getRuntime().availableProcessors() = " + Runtime.getRuntime().availableProcessors());

        //多线程里面, 通过new方式,产生消耗
        //1  newFixedThreadPool 一池5个线程
//        ExecutorService threadPool = Executors.newFixedThreadPool(5);
//         一池一线程
//        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        // 一池 不知道多少线程
        ExecutorService threadPool = Executors.newCachedThreadPool();
        // 模拟100个用户来办理业务,每个用户就是一个外部的请求的线程
        try {
            for (int i = 1; i <= 100; i++) {
                threadPool.submit(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务 ");
                    try {
                        TimeUnit.SECONDS.sleep(200);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            threadPool.shutdown();
        }
        //关闭比使用
        //2  newSingleThreadExecutor
        //3  newCachedThreadPool

    }
}
