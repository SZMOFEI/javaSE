package com.mofei.api.java._8.defaultInterface;

public class MyInterfaceTest {
    public static void main(String[] args) {
        MyInterface myInterface = new MyInterface() {

            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };

        double caculate = myInterface.calculate(100);
        double test = myInterface.sqrt(4);
        System.out.println("test = " + test);
        System.out.println("caculate = " + caculate);
    }
}
