package com.mofei.jvm.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 1.刷大厂的题目,基础的部分. 线程池底层使用的
 * ArayyBlockingQueue
 * LinkBlockinQueue  (有坑,元素太大了)
 * SynchronousQueue  (专属定制版,你不消费,我不生产)
 * 1/ add remove element (异常)
 * 2. offer poll peek (boolean) 温软版本
 * 3. put take 惹事版
 * 4. 特使
 * @author mofei
 * @date 2021/3/6 13:34
 */
public class BlockingQueueDemo {
    public static void main(String[] args) throws InterruptedException {
        //队列只有三个位置
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.offer("a",2, TimeUnit.SECONDS);
        blockingQueue.offer("b",2, TimeUnit.SECONDS);
        blockingQueue.offer("c",2, TimeUnit.SECONDS);


        //为什么有其他组的, 天天报异常
    }

    public void waitQueue() throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        blockingQueue.put("a");
        blockingQueue.put("b");
        blockingQueue.put("c");
        System.out.println("====================");
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
        blockingQueue.take();
    }
    public void booleanReturnGroup (){
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);

        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));
        System.out.println(blockingQueue.offer("e"));  //成功true 失败false

        System.out.println(blockingQueue.peek()); //探测队首

        blockingQueue.poll();
        blockingQueue.poll();
        blockingQueue.poll();
        System.out.println(blockingQueue.poll());
    }

    public void throwException() {
        //队列只有三个位置
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue(3);
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));
        System.out.println(blockingQueue.add("e"));  //成功true 失败false

        System.out.println(blockingQueue.element()); //探测队首

        blockingQueue.remove();
        blockingQueue.remove();
        blockingQueue.remove();
    }
}
