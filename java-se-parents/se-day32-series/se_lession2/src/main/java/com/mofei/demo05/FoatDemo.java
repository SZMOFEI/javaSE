package com.mofei.demo05;

/**
 * @author mofei
 * @date 2018/11/3 22:10
 */
public class FoatDemo {
    public static void main(String[] args) {
        //看到af的值是否变化
        float af = 5.2345666f;
        System.out.println("af = " + af);
        double a = 0.0;
        double c = Double.NEGATIVE_INFINITY;
        float d = Float.NEGATIVE_INFINITY;
        //Float 和Double的负无穷大是相等的
        System.out.println(c==d);
        System.out.println("a/a = " + a / a);
        //两个非数不相等的
        System.out.println(a / a == Double.NEGATIVE_INFINITY);
    }
}
