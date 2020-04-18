package com.mofei.api.java.lang;

import org.junit.Test;

/**
 * @author mofei
 * @date 2020/3/27 7:33
 */
public class DoubleTest {
    @Test
    public void parseDoubleTest() {
        Double d =  30.00;
        String s =  "30.00";
        double v = Double.parseDouble(s);
    }



}
