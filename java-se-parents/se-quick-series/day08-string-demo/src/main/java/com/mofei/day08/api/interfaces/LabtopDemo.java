package com.mofei.day08.api.interfaces;

/**
 * 需求:
 *      笔记本需要有开机,关机,提供Usb功能
 *      鼠标具有打开和关闭USB功能
 *      Usb接口具备的功能:打开和关闭
 *      键盘具有打开和关闭的功能
 * @author mofei
 * @date 2018/12/23 23:47
 */
public class LabtopDemo {
    public static void main(String[] args) {
        LabTop labTop = new LabTop();
        labTop.powerOn();
        Usb mouse = new Mouse();
        labTop.usbDevice(mouse);
        labTop.usbDevice(new KeyBoard());
        labTop.powerOff();
    }
}
