package com.mofei;

public class Car   {
    private String brand;
    private double price;

    public Car() {
        System.out.println("执行构造函数");
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        System.out.println("执行设置brand 方法");
        this.brand = brand;
    }

    public void init() {
        System.out.println("执行设置 init 方法");
    }

    public void destroy(){
        System.out.println("执行destroy 方法。。。");
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", price=" + price +
                '}';
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
