package com.mofei.pattern;

/**
 * @author mofei
 * @date 2018/11/8 23:28
 */
public class Coffee extends PrepareYinliaoTemplate {
    protected void brew() {
        System.out.println("咖啡泡製的方法，加入咖啡粉");
    }

    protected void addCondiment() {
        System.out.println(" 咖啡加入糖 " );
    }
}
