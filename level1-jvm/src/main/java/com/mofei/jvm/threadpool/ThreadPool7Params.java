package com.mofei.jvm.threadpool;

import java.util.concurrent.*;

/**
 * // 如果核心数和阻塞队列都满了. 那么6的线程加塞处理. 不等待队列中的任务.
 * @author mofei
 * @date 2021/3/7 22:35
 */
public class ThreadPool7Params {
    public static void main(String[] args) {
        // 常驻核心数量
        int corePoolSize = 2;
        //最大线程数
        int maximumPoolSize = 5;
        // 扩容后的线程空余后存活时间
        long keepAliveTime = 30;
        // 扩容后的线程空余后存活时间单位
        TimeUnit timeUnit = TimeUnit.SECONDS;
        // 超过核心线程数来不及处理的时候, 进入到阻塞队列当中
        BlockingQueue<Runnable> workQueue = new LinkedBlockingDeque<>(3);
        //threadFactory  创建线程的工程
        //rejectExcectionHandle  超过以后拒绝策略
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, timeUnit,
                workQueue, Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
        for (int i = 1; i <= 8; i++) {
            int finalI = i;
            poolExecutor.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "\t 正在执行业务" + finalI);
                try {
                    TimeUnit.SECONDS.sleep(4);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        poolExecutor.shutdown();
    }
}
