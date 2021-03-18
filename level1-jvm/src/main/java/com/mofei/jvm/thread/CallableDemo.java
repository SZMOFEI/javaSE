package com.mofei.jvm.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * 1.继承 , 2 实现接口  callable
 * 区别  : A 返回值
 * B :带返回值的线程结果. 支付接口. 起100个线程, 错误的两个是什么原因.
 * C: 抛异常
 * D:  run  和 call
 * // 1.装饰者的时候,有一个类实现了CALL 和Run的时候,能不能就解决了 FeatureTask 实现了Runable ,构造的时候传入一个CallAble
 * <p>
 * 2.为什么需要使用callAble呢  ,葫芦串的吃法, 中间有堵的时候, 单独开一个待会见的任务,避免堵塞
 * 希望获取结果的时候获取结果.
 *
 * @author mofei
 * @date 2021/3/7 15:25
 */
class MyThread implements Runnable {

    @Override
    public void run() {

    }
}

class MyThreadCallAble implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName()+" come in ");
        return 1024;
    }
}

public class CallableDemo {

    public static void main(String[] args) {
        MyThread t1 = new MyThread();
        t1.run();
        //callable的使用方式

        FutureTask<Integer> task = new FutureTask(new MyThreadCallAble());
        //多算的时候,需要起多个task
        Thread t2 = new Thread(task, "AA");
        Thread t3 = new Thread(task, "BB");
        t2.start();
        try {
            int result1 = 123;

            while (!task.isDone()) {

            }
            Integer integer = task.get();
            System.out.println("total = " + (integer + result1));

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
