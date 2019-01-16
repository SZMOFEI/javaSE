package com.mofei.singleTon;

/**
 * 假设场景：设计模式了解多少，能举例几个设计模式吗
 * <p>
 * 单例设计模式-01-懒汉式单例设计模式：
 * <p>
 * 要点：
 * 1.私有构造函数
 * 2.私有一个全局变量
 * 3.暴露一个共有方法，提供类实例
 * <p>
 * 提问：
 * 1.如何写一个单例？
 * 2.单例会存在什么问题，比如线程安全，效率
 * 3.根据上述问题如何解决
 * 4.struts和springmvc哪个是单例，哪个是多例的，为什么
 * 5.写完单例模式，如何测试单例是否成功？
 */
public class SingleTonDemo02 {


    private static SingleTonDemo02 singleTonDemo = null;

    private SingleTonDemo02() {
        System.out.println("构造函数被访问了");
    }

    public static synchronized  SingleTonDemo02 getSingleTonDemo() {
        if (singleTonDemo == null) {
            System.out.println("实例是空的");
            singleTonDemo = new SingleTonDemo02();
        }
        return singleTonDemo;
    }

}
