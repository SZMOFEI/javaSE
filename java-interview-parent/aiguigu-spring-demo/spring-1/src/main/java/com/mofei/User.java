package com.mofei;

public class User {
    private String name;

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void hello() {
        System.out.println("name = " + name);
    }

    public User(String name) {
        this.name = name;
    }
}
