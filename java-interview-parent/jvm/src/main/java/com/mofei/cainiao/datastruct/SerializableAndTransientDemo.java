package com.mofei.cainiao.datastruct;

import com.mofei.cainiao.base.Dog;

import java.io.*;

/**
 * 序列化和反序列化 https://www.jianshu.com/p/7acc99957e7f
 * 1.首先是序列化 , 如果想把内存中的内容传输成字符串或者文本, 没有序列化将报错
 * 如果没有序列化ID java.io.OptionalDataException
 * @author mofei
 * @date 2021/3/6 7:38
 */
public class SerializableAndTransientDemo {
    public static void main(String[] args) {
        //将文件写出
//        Dog changmao = new Dog("changmao", 11);
//        try {
//            new SerializableAndTransientDemo().writeToFile(changmao);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        try {
            Dog dog = new SerializableAndTransientDemo().readFromFile();
            System.out.println("dog = " + dog);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }

    public void writeToFile(Dog dog) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("aaa.txt");
        ObjectOutputStream stream = new ObjectOutputStream(outputStream);
        stream.write(dog.toString().getBytes());
        stream.flush();
        stream.close();
    }

    public Dog readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream inputStream = new FileInputStream("aaa.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        Dog dog = (Dog) objectInputStream.readObject();
        objectInputStream.close();
        return dog;
    }
}
