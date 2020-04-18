package com.mofei.api.java.map;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * HashMap 的测试类
 * 1.set(K key ,V value) 从类的声明中可以看到
 * 2.get(K key)
 * 3.addAll(Map<? extends K,? extends V) map)
 * 4.containsKey(K key)
 * 5.containsValue(Object obj)
 * 6.clear()
 * 7.entrySet()?
 * 8.remove(Object obj)
 * 9.Set<Entry<K,V>>  keySet();
 * 10.values()
 * @author mofei
 * @date 2020/4/4 0:05
 */
public class HashMapTest {
    @Test
    public void testAdd() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("a",1);
        map.put("b",1);
        map.put("a",2);
        Set<Map.Entry<String, Integer>> entries = map.entrySet();
        entries.forEach(x->{
            System.out.println("x.getKey() = " + x.getKey()+","+ x.getValue());
        });
    }

    class MyHashMap<K,V> {
        void set(K key,V value){

        };

    }
}
