package com.mofei.cainiao.object;

/**
 * @author mofei
 * @date 2021/3/6 6:14
 */
public class Employee {

    String name;
    int age;
    String description;
    double salary;

    public Employee(String name) {
        this.name = name;
    }

    public String printEmployee() {
        return "EmployEE{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", salary=" + salary +
                '}';
    }

    public Employee(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
