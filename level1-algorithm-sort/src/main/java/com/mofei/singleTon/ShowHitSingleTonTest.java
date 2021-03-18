package com.mofei.singleTon;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * 单例模式的测试：
 * 正常的情况瞎，使用的是懒汉式写法，因为构造函数私有化，所以无法通过new的形式实例化一个构造函数
 * 但是：java有反射的机制
 * 所以，通过下面的代码可以破坏单例模式，所以需要做策略，防止被反射破坏
 */
public class ShowHitSingleTonTest {
    public static void main(String[] args) {
        SingleTonDemo02 tonDemo1 = SingleTonDemo02.getSingleTonDemo();

        //这里是演示破坏单例模式的反射机制

        try {
            Constructor<SingleTonDemo02> constructor = SingleTonDemo02.class.getDeclaredConstructor();
            constructor.setAccessible(true);
            SingleTonDemo02 demo02 = constructor.newInstance();
            System.out.println("demo02.equals(tonDemo1) = " + demo02.equals(tonDemo1));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
