package com.mofei.singleTon;

/**
 * 防止反射的例子
 */
public class SingleTonDemo03 {


    public static class InnerClass {
        private static SingleTonDemo03 singleTonDemo = new SingleTonDemo03();
    }


    private SingleTonDemo03() {
        if (InnerClass.singleTonDemo!=null){
            throw new IllegalStateException("禁止通过反射实例化");
        }
    }

    public static synchronized SingleTonDemo03 getSingleTonDemo() {
        return InnerClass.singleTonDemo;
    }

}
