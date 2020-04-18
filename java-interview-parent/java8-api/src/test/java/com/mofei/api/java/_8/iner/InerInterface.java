package com.mofei.api.java._8.iner;

import org.junit.Test;

/**
 * 内建接口实现：
 * 1.Runable
 * 2.Comparator
 */
public class InerInterface {
    @Test
    public void testRunable() {
        Runnable r = ()->{
            System.out.println("sdfdsljfdsfjsd");
        };
        for (int i = 0; i < 500; i++) {
            r.run();
        }
    }
}
