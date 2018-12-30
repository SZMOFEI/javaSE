package com.mofei.day08.api.interfaces;

/**
 * @author mofei
 * @date 2018/12/23 23:50
 */
public class KeyBoard implements Usb{
    public void close() {
        System.out.println("关闭键盘");
    }


    public void open() {
        System.out.println("打开键盘");
    }
}
