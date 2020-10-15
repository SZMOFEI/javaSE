package org.example.jdk001;


import sun.misc.Launcher;

import java.net.URL;

/**
 * @author mofei
 * @date 2020/10/15 18:28
 */
public class TestJDKClassLoader {
    // String 类加载器名称
    //DESKeyFactory 类加载器名称是什么
    //USER   类加载器名称
    public static void main(String[] args) {
        System.out.println("String.class.getClassLoader() = " + String.class.getClassLoader());
        System.out.println("DESKeySpec.class.getClassLoader() = " + com.sun.crypto.provider.DESKeyFactory.class.getClassLoader());
        System.out.println("User.class.getClassLoader() = " + User.class.getClassLoader());

        System.out.println();
        ClassLoader appClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader extClassloader = appClassLoader.getParent();
        ClassLoader bootstrapLoader = extClassloader.getParent();
        System.out.println("the bootstrapLoader : " + bootstrapLoader);
        System.out.println("the extClassloader : " + extClassloader);
        System.out.println("the appClassLoader : " + appClassLoader);

        System.out.println();
        System.out.println("bootstrapLoader加载以下文件：");
        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
        for (URL urL : urLs) {
            System.out.println("urL = " + urL);
        }

        System.out.println();
        System.out.println("extClassloader加载以下文件：");
        System.out.println( System.getProperty("java.ext.dirs"));

        System.out.println();
        System.out.println("appClassloader加载以下文件：");
        System.out.println( System.getProperty("java.class.path"));

    }
}
