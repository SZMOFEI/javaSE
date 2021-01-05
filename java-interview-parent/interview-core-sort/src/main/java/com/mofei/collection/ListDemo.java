package com.mofei.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Stream;

/**
 * 这里演示List 的例子
 * collection接口当中包含的基础抽象方法:
 * 1.size()    2.isEmpty()     3.Contain()     4.iterater()    5.add()
 * 6.remove()  7.toArray()     8.containAll()  9.addAll()      10.removeIf()
 * 11.clear()   12.equals   13.hashCode()  14.spritIterator     15.stream()
 * 16.prepallStream()
 *
 * @author com.mofei
 * @version 2019/1/19 9:38
 */
public class ListDemo {
    List<Integer> intList = new ArrayList<Integer>();

    @Test
    public void testStream() {
        intList.add(1);
        intList.add(2);
        intList.add(3);
    }

    @Test
    public void testContainAll() {
        intList.add(1);
        intList.add(2);
        intList.add(3);
        Integer[] arr = {1, 2};
        List<Integer> ints = Arrays.asList(arr);
        boolean contains = intList.containsAll(ints);
        System.out.println("contains = " + contains);
    }

    @Test
    public void testToArrayT() {
        intList.add(1);
        intList.add(2);
        Integer[] array = intList.toArray(new Integer[]{});
        for (Integer integer : array) {
            System.out.println("integer = " + integer);
        }
    }

    @Test
    public void testToArray() {
        intList.add(1);
        intList.add(2);
        Object[] array = intList.toArray();
        for (int i = 0; i < array.length; i++) {
            System.out.println("array = " + array[i]);
        }
    }

    @Test
    public void testIterrator() {
        intList.add(1);
        Iterator<Integer> iterator = intList.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            System.out.println("next = " + next);
        }
    }

    @Test
    public void testSize() {
        ArrayList<Integer> intList = new ArrayList<Integer>();
        intList.add(1);
        int size = intList.size();
        System.out.println("list 的size = " + size);
    }

    @Test
    public void testEmpty() {
        intList = new ArrayList<Integer>();
        intList.add(1);
        boolean empty = intList.isEmpty();
        System.out.println("list 的empty = " + empty);
    }

    @Test
    public void testContain() {
        Integer[] arr = {1, 4, 6, 8, 3};
        List<Integer> list = Arrays.asList(arr);
        boolean contains = intList.contains(4);
        System.out.println("contains = " + contains);
    }

}
