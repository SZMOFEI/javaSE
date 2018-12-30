package com.mofei.pattern;

/**
 * @author mofei
 * @date 2018/11/8 23:29
 */
public class Tea extends PrepareYinliaoTemplate {
    protected void brew() {
        System.out.println("加入綠色的茶葉炮製茶" );
    }

    protected void addCondiment() {
        System.out.println("茶葉中需要加入少量檸檬");
    }

    @Override
    protected boolean isCustomerWants() {

        return false;
    }

}
