package com.mofei.chapter5.c5_9静态初始化;

/**
 * @author mofei
 * @date 2018/11/17 19:37
 */
public class Mid extends Root {
    static {
        System.out.println("Mid 静态初始化块 ");
    }
    {
        System.out.println("Mid 普通初始化块 ");
    }
    public Mid(){
        System.out.println("Mid 无参构造函数");
    }
    public  Mid(String msg){
        System.out.println("Mid 的带参数构造函数,参数值是: " + msg);
    }

}
