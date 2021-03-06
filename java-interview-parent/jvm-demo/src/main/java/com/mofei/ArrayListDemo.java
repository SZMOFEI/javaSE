package com.mofei;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author mofei
 * @date 2021/3/2 9:25
 */
public class ArrayListDemo {
    public static void main(String[] args) {
//        seeListNoSafe();
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < 3000; i++) {
            new Thread(() -> {
                map.put("a", Thread.currentThread().getName());
                System.out.println("map = " + map);
            }, "threadName").start();
        }
    }

    private static void seeListNoSafe() {
        List<Object> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                list.add("s");
                System.out.println("list = " + list);
            }, "" + i).start();
        }
    }
}
