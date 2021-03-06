package com.mofei.cainiao.object;

/**
 * @author mofei
 * @date 2021/3/6 6:16
 */
public class EmployeeTest {
    public static void main(String[] args) {
        Employee empOne = new Employee("RUNOOB1");
        Employee empTwo = new Employee("RUNOOB2");
        // 调用这两个对象的成员方法
        empOne.setAge(26);
        empOne.setDescription("高级程序员");
        empOne.setSalary(1000);
        String employee = empOne.printEmployee();
        System.out.println("employee = " + employee);

        empTwo.setAge(21);
        empTwo.setDescription("菜鸟程序员");
        empTwo.setSalary(500);
        String employee1 = empTwo.printEmployee();
        System.out.println("employee1 = " + employee1);
    }
}
