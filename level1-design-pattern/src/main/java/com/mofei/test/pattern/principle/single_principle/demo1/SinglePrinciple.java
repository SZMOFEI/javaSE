package com.mofei.test.pattern.principle.single_principle.demo1;

/**
 * @author com.mofei
 * @version 2020/4/19 4:32
 */
public class SinglePrinciple {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("飞机");
        vehicle.run("轮船");
        vehicle.run("汽车");
    }

    static  class Vehicle {
        public void  run (String name){
            System.out.println(name+" 正在陆地上跑");
        }
    }
}
