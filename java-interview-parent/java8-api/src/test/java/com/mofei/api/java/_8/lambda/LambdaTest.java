package com.mofei.api.java._8.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
    @Test
    public void testLambda() {
        List<Integer> strings = Arrays.asList(13, 12, 19, 20, 34);
        strings.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        strings.forEach(System.out::println);
    }

    @Test
    public void testSort2() {
        List<Integer> strings = Arrays.asList(13, 12, 19, 20, 34);
        strings.sort(Comparator.comparingInt(o -> o));
        strings.forEach(System.out::println);
    }

    @Test
    public void name() {
        List<Integer> strings = Arrays.asList(13, 12, 19, 20, 34);
        strings.sort(Comparator.comparingInt(o->-o));
        strings.forEach(System.out::println);

    }
}
