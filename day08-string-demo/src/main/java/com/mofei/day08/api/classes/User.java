package com.mofei.day08.api.classes;

/**
 * @author mofei
 * @date 2018/12/23 22:53
 */
public class User {
    private String name;
    private  Integer money;

    public User(String name, Integer money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }

    public void show(){
        System.out.println("toString() = " + toString());
    }


    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public User() {

    }
}
