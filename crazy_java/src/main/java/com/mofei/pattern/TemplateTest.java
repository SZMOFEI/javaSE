package com.mofei.pattern;

/**
 * @author mofei
 * @date 2018/11/8 23:30
 */
public class TemplateTest {
    public static void main(String[] args) {
        System.out.println(" 開始製作咖啡。。。。。 " );
        PrepareYinliaoTemplate b1 = new Coffee();
        b1.prepareYinliaoTemple();
        System.out.println(" 咖啡製作完畢。。。。。 " );
        System.out.println("##################################################" );


        System.out.println("開始製作茶葉");
        PrepareYinliaoTemplate p2 = new Tea();
        p2.prepareYinliaoTemple();
        System.out.println("製作茶葉完畢........");
    }
}
