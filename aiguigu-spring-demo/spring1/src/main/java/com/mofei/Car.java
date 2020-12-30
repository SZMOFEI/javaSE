package com.mofei;

public class Car {
    private String brand;
    private String corp;
    private String price;
    private int maxSpeed;

    public Car() {
    }

    public Car(String brand, String corp, String price) {
        this.brand = brand;
        this.corp = corp;
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }


    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", corp='" + corp + '\'' +
                ", price='" + price + '\'' +
                ", maxSpeed=" + maxSpeed +
                '}';
    }

    public Car(String brand, String corp, int maxSpeed) {
        this.brand = brand;
        this.corp = corp;
        this.maxSpeed = maxSpeed;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCorp() {
        return corp;
    }

    public void setCorp(String corp) {
        this.corp = corp;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(int maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
