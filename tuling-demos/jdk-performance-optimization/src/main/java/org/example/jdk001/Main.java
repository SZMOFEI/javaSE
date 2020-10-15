package org.example.jdk001;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mofei
 * @date 2020/10/15 19:22
 */
public class Main {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        MyClassLoader myClassLoader = new MyClassLoader("D:/test");
        Class<?> aClass = myClassLoader.loadClass("org.example.jdk001.Math");
        Object o = aClass.newInstance();
        Method sout = aClass.getDeclaredMethod("compute", null);
        sout.invoke(o, null);
        System.out.println("aClass.getClassLoader() = " + aClass.getClassLoader().getClass().getName());


    }
}
