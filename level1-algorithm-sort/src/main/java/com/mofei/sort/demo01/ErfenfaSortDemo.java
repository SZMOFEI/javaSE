package com.mofei.sort.demo01;

import org.junit.Test;

import java.util.Arrays;

/**
 * 手写二分法排序算法：
 * 需求：
 * 跟定一个数字 15，查找在数组中的位置 ，找不到返回-1
 * 思路分析：
 * 使用二分法的要求是：首先，假设表中元素是按升序排列,重要，假定这个元素的是有序的
 */
public class ErfenfaSortDemo {


    @Test
    public void sortTest() {
        int[] arr = {6, 3, 7, 8, 3, 9, 3, 14, 47, 68, 65, 43, 45, 33, 23, 26, 27, 4};
        Arrays.sort(arr);
        int dest =45;
        int index = doSort(arr, dest);
        System.out.println("目标dest: "+dest+"，index = " + index+",index  对应的元素是"+arr[index]);
    }

    /**
     * 二分法排序算法
     *
     * @param source
     * @return
     */
    public int doSort(int[] source, int dest) {
        //1.定义最大索引和最小索引
        //2.求出中间索引
        //3.遍历主要最低小于等于最大索引，就一直进行匹配，避免重复
        //4.如果目标比中间索引值大，那么就要需要重新定义最低索引，等于middle+1；
        //5.如果是目标比中间索引的值小，那么就需要重新定义最高索引
        int low = 0;
        int high = source.length - 1;
        int middle;
        while (low <= high) {
            middle = (low + high) >>> 1;
            if (dest==source[middle]){
                return middle;
            }else if (dest<source[middle]){
                high= middle-1;
            }else {
                low= middle+1;
            }
        }
        return -1;
    }

    @Test
    public void test01() {
        int i = 6 >>> 1;
        System.out.println("i = " + i);
    }

}
