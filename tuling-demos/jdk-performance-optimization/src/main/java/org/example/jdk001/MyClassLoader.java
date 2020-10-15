package org.example.jdk001;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author mofei
 * @date 2020/10/15 19:11
 */
public class MyClassLoader extends ClassLoader {
    private final String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    /**
     * 自定义类加载器
     *
     * @param name 类名
     * @return 查找到的类对象
     * @throws ClassNotFoundException e
     */
    public Class<?> findClass(final String name)
            throws ClassNotFoundException {

        byte[] data = new byte[0];
        try {
            data = loadByte(name);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //defineClass方法接收一个字节数组， 是这个字节码文件读取最后的文件
        return defineClass(name, data, 0, data.length);
    }

    private byte[] loadByte(String name) throws IOException {
        if (name.isEmpty()) {
            return new byte[0];
        }
        name = name.replaceAll("\\.", "/");
        FileInputStream fileInputStream = new FileInputStream(classPath + "/" + name + ".class");
        int available = fileInputStream.available();
        byte[] bytes = new byte[available];
        // java.sample.Math.class 中读取成byte[]。
        fileInputStream.read(bytes);
        fileInputStream.close();
        return bytes;
    }

}
