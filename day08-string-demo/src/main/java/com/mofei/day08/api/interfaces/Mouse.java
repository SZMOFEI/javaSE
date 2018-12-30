package com.mofei.day08.api.interfaces;

/**
 * @author mofei
 * @date 2018/12/23 23:48
 */
public class Mouse implements Usb {
    public void close() {
        System.out.println("关闭鼠标");
    }

    public void open() {

        System.out.println("打开鼠标");
    }
}
