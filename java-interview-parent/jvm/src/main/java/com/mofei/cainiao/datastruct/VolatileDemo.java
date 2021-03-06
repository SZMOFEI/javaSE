package com.mofei.cainiao.datastruct;

/**
 * 1.首先是第一个线程进入 , 然后睡眠
 * 2.第二个线程开始进入,修改这个状态, 等待第一个线程
 *
 * @author mofei
 * @date 2021/3/6 8:24
 */
public class VolatileDemo implements Runnable {
    private volatile boolean active;


    @Override
    public void run() {
        active = true;
        while (active) {
//            System.out.println(Thread.currentThread().getName() + " ;active = " + active);
        }
    }

    public void stop() {
        active = false;
    }

    public static void main(String[] args) {


        new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            new VolatileDemo().stop();
            System.out.println("stop 执行完");
        }).start();
        new Thread(new VolatileDemo()).start();
    }
}

