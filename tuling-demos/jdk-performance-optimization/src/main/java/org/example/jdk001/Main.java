package org.example.jdk001;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mofei
 * 出现了一个问题就是在windows系统下， 在org.example.jdk001.Math 转成org/example/jdk001/Math 中出现一个错误
 * 总结如下：首先自定义一个MyClassLoader extents ClassLoader
 * 2.重新实现findClass方法
 * 3.其中核心的是根据类和存放路径将字节码文件转成byte[]数据源
 * 4.传入defineClasss方法（name,data,0,data.length()）
 * @date 2020/10/15 19:22
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        //实例化一个自定义的类加载器
        MyClassLoader myClassLoader = new MyClassLoader("D:/test");
        //尝试加载一个自定义的Math类，此类存放在磁盘某个位置
        Class<?> aClass = myClassLoader.loadClass("org.example.jdk001.Math");
        //实例化一个自定义类
        Object o = aClass.newInstance();
        //反射获取方法
        Method sout = aClass.getDeclaredMethod("compute", null);
        //反射调用方法
        sout.invoke(o, null);
        //打印一下此类使用的类加载器是否自定义的类加载器
        System.out.println("aClass.getClassLoader() = " + aClass.getClassLoader().getClass().getName());
    }
}
