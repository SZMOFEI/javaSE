package com.mofei.chapter5.c5_7_1多态性;

/**
 * @author mofei
 * @date 2018/11/6 16:53
 */
public class PolymorphismDemo extends BaseClass{
    public String book = "实战";
    @Override
    public void test(){
        System.out.println("子类被覆盖的方法");
    }
    public void sub(){
        System.out.println("子类的普通方法");
    }

    public static void main(String[] args) {
        BaseClass baseClass = new BaseClass();
        baseClass.base();
        baseClass.test();

        PolymorphismDemo sub = new PolymorphismDemo();
        System.out.println("sub.book = " + sub.book);
        sub.base();
        sub.test();

        BaseClass polymorphismBc = new PolymorphismDemo();
        System.out.println("polymorphismBc.book = " + polymorphismBc.book);
        polymorphismBc.test();
        polymorphismBc.base();
    }
}
