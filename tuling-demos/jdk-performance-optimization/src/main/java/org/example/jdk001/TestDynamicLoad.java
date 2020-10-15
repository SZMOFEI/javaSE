package org.example.jdk001;

/**
 * @author mofei
 * @date 2020/10/15 18:24
 */

public class TestDynamicLoad {

    static {
        System.out.println("*************load TestDynamicLoad************");
    }

    public static void main(String[] args) {
        new A();
        System.out.println("*************load test************");
        B b = new B(); //B不会加载，除非这里执行 new B() 11 } 12 } 1314
    }
}

class A {

    static {
        System.out.println("*************load A************");

    }

    public A() {
        System.out.println("*************initial A************");

    }
}

class B {

    static {
        System.out.println("*************load B************");
    }

    public B() {
        System.out.println("*************initial B************");

    }
}
