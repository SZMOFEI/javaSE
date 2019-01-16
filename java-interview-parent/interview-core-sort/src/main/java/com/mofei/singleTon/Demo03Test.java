package com.mofei.singleTon;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 演示防止反射单例模式的例子
 * 预期的结果是抛出了异常  IllegalStateException
 */
public class Demo03Test {
    public static void main(String[] args) {

    }


    @Test
    public  void test01() {
        SingleTonDemo03 demo = SingleTonDemo03.getSingleTonDemo();
        try {
            Constructor<SingleTonDemo03> constructor = SingleTonDemo03.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingleTonDemo03 singleTonDemo03 = constructor.newInstance();

            System.out.println("singleTonDemo03.equals(demo) = " + singleTonDemo03.equals(demo));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
