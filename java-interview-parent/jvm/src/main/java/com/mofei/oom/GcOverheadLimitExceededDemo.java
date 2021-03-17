package com.mofei.oom;

import java.util.ArrayList;
import java.util.List;

/**
 * GC在频繁发生, 但是回收效率不佳的时候
 * 1.首先把内存设置小一些
 * 2.不断地产生对象,并且回收
 * 3.
 *
 * @author mofei
 * @date 2021/3/11 23:41
 */
public class GcOverheadLimitExceededDemo {
    public static void main(String[] args) {
        int i = 0;
        List<Object> list = new ArrayList<>();
        try {
            while (true) {
                list.add(String.valueOf(++i).intern());
            }
        } catch (Exception e) {
            System.out.println("i = " + i);
            e.printStackTrace();
            throw e;
        }
    }
}
