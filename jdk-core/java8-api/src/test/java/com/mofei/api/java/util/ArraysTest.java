package com.mofei.api.java.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author com.mofei
 * @version 2020/3/27 7:33
 */
public class ArraysTest {
    @Test
    public void testArray1() {

        List<Integer> integers = Arrays.asList(1, 3, 9, 6, 7);

        Arrays.sort(integers.toArray(new Integer[0]));
//        Arrays.parallelSort();
        for (Integer integer : integers) {
            System.out.println("integer = " + integer);
        }

        integers.sort(Comparator.comparingInt(Integer::intValue));

        for (Integer integer : integers) {
            System.out.println("integer 2= " + integer);
        }
        List<String> strings = Arrays.asList("a", "b", "c", "d");

    }


}
