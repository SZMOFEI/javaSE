package com.mofei.singleTon;

/**
 * 假设场景：设计模式了解多少，能举例几个设计模式吗
 *
 * 单例设计模式-01-饿汉式单例设计模式：
 *
 * 要点：
 *  1.私有构造函数
 *  2.私有一个全局变量
 *  3.暴露一个共有方法，提供类实例
 *
 * 提问：
 *  1.如何写一个单例？
 *  2.单例会存在什么问题，比如线程安全，效率
 *  3.根据上述问题如何解决
 *  4.struts和springmvc哪个是单例，哪个是多例的，为什么
 *  5.写完单例模式，如何测试单例是否成功？
 */
public class SingleTonDemo01 {

    private final static SingleTonDemo01 singleTonDemo=new SingleTonDemo01();


    private SingleTonDemo01() {

    }

    public static SingleTonDemo01 getSingleTonDemo() {
        return singleTonDemo;
    }

    public static void main(String[] args) {
        SingleTonDemo01 demo01 = SingleTonDemo01.getSingleTonDemo();
        SingleTonDemo01 demo02 = SingleTonDemo01.getSingleTonDemo();
        System.out.println("demo01.equals(demo02) = " + demo01.equals(demo02));
    }
}
