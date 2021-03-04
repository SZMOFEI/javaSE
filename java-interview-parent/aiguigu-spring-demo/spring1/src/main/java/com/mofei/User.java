package com.mofei;

public class User {
    private String name;

    public User() {
        System.out.println("执行无参数构造");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        System.out.println("执行了设置值的方法");
        this.name = name;
    }

    public void hello() {
        System.out.println("name = " + name);
    }

    public User(String name) {
        System.out.println("执行 有参数构造");
        this.name = name;
    }
}
