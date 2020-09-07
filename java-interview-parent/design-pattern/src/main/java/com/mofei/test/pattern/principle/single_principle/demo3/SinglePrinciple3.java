package com.mofei.test.pattern.principle.single_principle.demo3;

/**
 * @author com.mofei
 * @date 2020/4/19 4:49
 */
public class SinglePrinciple3 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.runAir("飞机");
        vehicle.runWater("轮船");
        vehicle.runRoald("汽车");
    }

    static class Vehicle {
        public void runRoald(String name) {
            System.out.println(name + "正在地上跑");
        }

        public void runAir(String name) {
            System.out.println(name + "正在空中飞");
        }

        public void runWater(String name) {
            System.out.println(name + "正在水中前进");
        }
    }
}
