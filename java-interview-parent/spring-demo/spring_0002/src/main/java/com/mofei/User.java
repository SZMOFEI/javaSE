package com.mofei;

public class User {
    private String name;
    private  int  age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void init() {
        System.out.println("执行user 初始化");
    }

    public  void destroy () {
        System.out.println("执行user destroy 方法 ");
    }
    @Override
    public String toString(){
        return "User [name=" + name + ", age=" + age + "]";
    }

}
