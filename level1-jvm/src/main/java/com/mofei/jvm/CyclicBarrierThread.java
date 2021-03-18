package com.mofei.jvm;

import java.util.Map;
import java.util.concurrent.*;

public class CyclicBarrierThread implements Runnable {

    private ConcurrentHashMap<String, Integer> sheetBankCountCacheMap = new ConcurrentHashMap<>();
    private CyclicBarrier cyclicbarrier = new CyclicBarrier(30, this);

    @Override
    public void run() {
        //计算结果
        int res = 0;
        for (Map.Entry<String, Integer> entry : sheetBankCountCacheMap.entrySet()) {
            res += entry.getValue();
        }
        System.out.println("res = " + res);
    }

    //声明一个线程池
    private Executor executor = Executors.newFixedThreadPool(30);

    public void count() {
        for (int i = 0; i < 30; i++) {
            int finalI = i;
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    //1.处理每个线程复杂逻辑，拿到结果
                    //2.将结果存入
                    sheetBankCountCacheMap.put(finalI + "", 1);
                    //3.cyclicbarrier等待
                    try {
                        cyclicbarrier.await();
                        System.out.println("finalI = " + finalI);//等待已经同行了
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

}
