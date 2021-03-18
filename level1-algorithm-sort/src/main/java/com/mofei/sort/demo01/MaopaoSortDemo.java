package com.mofei.sort.demo01;

import org.junit.Test;

import java.util.Arrays;

/**
 * 手写冒泡排序算法：
 * 需求：
 * 跟定一个数组，重新排序，从大到小进行排序
 * 思路分析：
 * 使用冒泡的原理是：
 * 1.排序前【1，5，9，2，14，50，90】
 * a.第一个循环有arr.length-1个下标，因为从0开始的
 * b.arr[0]  arr[5-0-1] 相比，如果第一个和最后一个相比，如果第一个比后面的大，那么就调换位置
 * c.如何调换位置呢，声明一个临时变量 temp  先将最后一个元素赋值给temp,然后将第一个元素赋值给第一个元素，然后将temp赋值给最后一个元素，这就可以实现案例元素的换位
 */
public class MaopaoSortDemo extends SortTemplate {
    @Test
    public void test01() {
        int[] arr = {6, 3, 7, 8, 3, 9, 3, 14, 47, 68, 65, 43, 45, 33, 23, 26, 27, 4};
        int[] result = sort(arr);

    }


    /**
     * 冒泡排序算法
     *
     * @param source
     * @return
     */
    @Override
    public int[] doSort(int[] source) {
        //因为数组的下标是从0开始的，所以需要-1防止数组越界
        int temp;
        for (int i = 0; i < source.length - 1; i++) {
            for (int j = 0; j < source.length - i - 1; j++) {
                if (source[j] > source[j + 1]) {
                    temp = source[j];
                    source[j] = source[j + 1];
                    source[j + 1] = temp;
                }
            }
        }
        return source;
    }

}
