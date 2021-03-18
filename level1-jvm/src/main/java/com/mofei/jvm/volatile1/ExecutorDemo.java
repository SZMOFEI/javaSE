package com.mofei.jvm.volatile1;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author mofei
 * @date 2021/3/4 23:37
 */
public class ExecutorDemo {
    private static AtomicInteger success = new AtomicInteger();
    private static ConcurrentHashMap<String, String> failedCacheMap = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            int retryCountPerTime = 49;
            int count = 0;
            if (!failedCacheMap.isEmpty()) {
                for (Map.Entry<String, String> entry : failedCacheMap.entrySet()) {
                    if (count > retryCountPerTime) {
                        continue;
                    }
                    String sn = entry.getKey();
                    String privateKey = entry.getKey();
                    //执行重试链接
                    new Thread(() -> {
                        System.out.println("开始执行重新链接，线程名称是：" + sn);
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(sn + "重新链接成功！");
                        failedCacheMap.remove(sn);
                        success.getAndIncrement();
                    }).start();
                    count++;
                }
            }
            System.out.println("check task is runnig ,failedCacheMap is  " + failedCacheMap.size() + " ； success is " + success + " 线程名称是 = " + Thread.currentThread().getName() + " ; 时间是：" + new Date());
        }, 0, 5, TimeUnit.SECONDS);

        for (int i = 0; i < 10000; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(finalI + "开始执行IOT链接");
                if (finalI % 5 == 0) {
                    failedCacheMap.put(finalI + "", finalI + "");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(finalI + " 执行链接成功");
                    success.getAndIncrement();
                }
            }).start();
        }
    }
}
