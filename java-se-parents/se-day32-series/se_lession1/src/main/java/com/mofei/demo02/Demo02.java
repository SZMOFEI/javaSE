package com.mofei.demo02;

/**
 * java 的程序注释:
 * 1.单行注释
 * 2.多行注释
 * 3.块注释
 * @author mofei
 * @date 2018/12/30 16:41
 */
public class Demo02 {
    /**
     * 这是一个文档注释
     * @param args 参数
     */
    public static void main(String[] args) {
        //1.这是一个单行注释,编译器不会知道
        System.out.println("args = " + args);
        /*
        这个块注释,没有被编译进去
         */
    }
}
