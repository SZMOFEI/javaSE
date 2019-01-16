package com.mofei.sort.demo01;

import java.util.Arrays;

public abstract class SortTemplate {

    public int[] sort(int[] source) {
        System.out.println("排序前的的数组是：" + Arrays.toString(source).toString());
        int[] result = doSort(source);
        System.out.println("排序后的的数组是：" + Arrays.toString(result).toString());
        return result;

    }

    abstract int[] doSort(int[] source);
}
