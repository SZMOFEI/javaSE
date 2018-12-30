package com.mofei.day08.api.interfaces;

/**
 * @author mofei
 * @date 2018/12/23 23:50
 */
public class LabTop {
    public void powerOff() {

        System.out.println("关闭电脑");
    }
    public void powerOn() {
        System.out.println("打开电脑");
    }

    public void usbDevice(Usb usb){
        usb.open();
        usb.close();
    }

}
