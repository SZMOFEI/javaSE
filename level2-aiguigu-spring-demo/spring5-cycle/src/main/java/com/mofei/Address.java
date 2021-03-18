package com.mofei;

public class Address {
    public Address() {
        System.out.println("正在执行初始化构造函数");
    }

    private String city;
    private String street;

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public void init() {
        System.out.println("正在执行初始化方法");
    }

    public void destroy() {
        System.out.println("正在destroy方法");
    }

    public void setCity(String city) {
        System.out.println("正在执行setCity方法");
        this.city = city;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                '}';
    }

    public void setStreet(String street) {
        System.out.println("正在执行setStreet方法");
        this.street = street;
    }
}
