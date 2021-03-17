package com.mofei.oom;

import sun.misc.VM;

import java.nio.ByteBuffer;

/**
 * 当直接内存, 也就是不属于JVM管理的这部分内存超过最大值的时候.
 * 会爆出这个异常, 在NIO编程特别注意
 * Exception in thread "main" java.lang.OutOfMemoryError: Direct buffer memory
 * @author mofei
 * @date 2021/3/11 23:48
 */
public class DirectBufferMemoryDemo {
    public static void main(String[] args) {
        System.out.println("sun.misc.VM.maxDirectMemory() = " + VM.maxDirectMemory()/1024/1024);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 6);
    }
}
