package com.mofei.api.java._8.fountion;


public class MyConverterTest {
    public static void main(String[] args) {
        MyConverter<String, Integer> my = String::valueOf;
        String convert = my.convert(300);
        System.out.println("convert = " + convert);
    }

}
