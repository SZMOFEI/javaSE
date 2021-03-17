package com.mofei.oom;

/**
 * 无法创建线程了
 * finalI = 151810
 * Exception in thread "main" java.lang.OutOfMemoryError: unable to create new native thread
 * 	at java.lang.Thread.start0(Native Method)
 * 	at java.lang.Thread.start(Thread.java:717)
 * 	at com.mofei.oom.UnableCreateNativeThreadDemo.main(UnableCreateNativeThreadDemo.java:19)
 * @author mofei
 * @date 2021/3/11 23:53
 */
public class UnableCreateNativeThreadDemo {
    public static void main(String[] args) throws InterruptedException {
        try {
            for (int i = 0; ; i++) {
                int finalI = i;
                new Thread(() -> {
                    System.out.println("finalI = " + finalI);
                    try {
                        Thread.sleep(Integer.MAX_VALUE);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}
