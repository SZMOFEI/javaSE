package com.mofei.api.java.lang;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author com.mofei
 * @version 2020/3/27 7:33
 */
public class BooleanTest {
    @Test
    public void newBooleanTest() {
        String s = "false";
        String s2 = "true";
        Boolean aBoolean = Boolean.valueOf(s);
        if (!Boolean.valueOf(s)){
            System.out.println("aBoolean = " + aBoolean);
        }
    }

    @Test
    public void testCompared() {
        boolean b = true;
        boolean a = true;
        boolean c = false;
        int compare = Boolean.compare(a, b);
        int compare1= Boolean.compare(a, c);
        //相等是0
        System.out.println("compare = " + compare);
        //相等是1
        System.out.println("compare1 = " + compare1);
    }


    @Test
    public void testLogicAdd() {
        boolean b = true;
        boolean a = true;
        boolean c = false;
        boolean d = false;
        boolean b1 = Boolean.logicalAnd(a, b);
        boolean b2 = Boolean.logicalAnd(a, c);
        boolean b3 = Boolean.logicalOr(a, c);
        boolean b4 = Boolean.logicalXor(c, d);
        Assert.assertTrue(b1);
        Assert.assertTrue(!b2);
//        Assert.assertTrue(b2);
        Assert.assertTrue(b3);
        System.out.println("b4 = " + b4);
        Assert.assertTrue(b4);
    }
}
