package com.mofei.aop.calculator;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        System.out.println("Murphy 执行 ADD 方法之前，参数是[ " + i + " , " + j+"]");
        int result = i + j;
        System.out.println("Murphy 执行 ADD 方法之后，结果 result = " + result);
        return result;
    }

    @Override
    public int sub(int i, int j) {
        System.out.println("Murphy 执行 sub 方法之前，参数是[ " + i + " , " + j+"]");
        int result = i - j;
        System.out.println("Murphy 执行 sub 方法之后，结果 result = " + result);
        return result;
    }

    @Override
    public int mul(int i, int j) {
        System.out.println("Murphy 执行 mul 方法之前，参数是[ " + i + " , " + j+"]");
        int result = i * j;
        System.out.println("Murphy 执行 mul 方法之后，结果 result = " + result);
        return result;
    }

    @Override
    public int div(int i, int j) {
        System.out.println("Murphy 执行 div 方法之前，参数是[ " + i + " , " + j+"]");
        int result = i / j;
        System.out.println("Murphy 执行 div 方法之后，结果 result = " + result);
        return result;
    }
}
