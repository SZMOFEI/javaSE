package com.mofei.demo;

import com.mofei.demo.entity.Employee;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * lambada测试
 *
 * @author com.mofei
 * @version 2020/3/11 17:04
 */
public class LambadaTest {
    @Test
    public void testLamber() {
        List<Employee> employees = initEmployees();
        List<Employee> emps = new ArrayList<Employee>();
        List<Employee> emps2 = new ArrayList<Employee>();
        List<Employee> byAge = filterEmpByAge(employees, emps);
        for (Employee emp : byAge) {
            System.out.println("emp = " + emp);
        }
        System.out.println("--------------------");
        List<Employee> bySalary = filterEmpBySalary(employees, emps2);
        for (Employee employee : bySalary) {
            System.out.println("employee = " + employee);
        }
    }

    private List<Employee> initEmployees() {
        return Arrays.asList(
                new Employee("ben", 11, 3000),
                new Employee("xiaohong", 15, 3500),
                new Employee("xiaoming", 19, 1200),
                new Employee("dada", 24, 1200)
        );
    }

    private List<Employee> filterEmpByAge(List<Employee> employees, List<Employee> emps) {
        for (Employee employee : employees) {
            if (employee.getAge() > 15) {
                emps.add(employee);
            }
        }
        return emps;
    }

    private List<Employee> filterEmpBySalary(List<Employee> employees, List<Employee> emps) {
        for (Employee employee : employees) {
            if (employee.getSalary() > 2000) {
                emps.add(employee);
            }
        }
        return emps;
    }
}
