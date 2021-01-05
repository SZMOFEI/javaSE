package com.mofei.exception;

import org.junit.Test;

import java.net.BindException;

/**
 * @Classname ExceptionDemo02
 * @Description
 * 异常展示类
 *   异常分为编译异常，运行时异常
 *   1.NullPointException
 *   2.Index Of boundException
 *   3.ClassNotFoundException
 *   4.BindException
 *   5.ClassCastException
 *   6.IllegalArgumentException
 *   7.BufferOverFowException
 *   8.ArrayStoreException
 *   9.ArithmeticException / by zero 算术异常
 *
 * @version 2019/1/17 18:17
 * @Created by HELLO_MOFEI
 */
public class ExceptionDemo02 {

    public static void main(String[] args) {

    }

    /**
     *算术异常
     * @throws Exception
     */
    @Test
    public void fun() throws Exception{
        int i  = 0 ;
        System.out.println(" = " +i/0);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void fun01() throws Exception{

    }
}
