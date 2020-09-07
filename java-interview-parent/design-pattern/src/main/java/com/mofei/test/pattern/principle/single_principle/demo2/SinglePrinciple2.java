package com.mofei.test.pattern.principle.single_principle.demo2;

/**
 * @author com.mofei
 * @date 2020/4/19 4:44
 */
public class SinglePrinciple2 {
    public static void main(String[] args) {
        AriVehicle ariVehicle = new AriVehicle();
        ariVehicle.run("飞机");
        WaterVehicle waterVehicle = new WaterVehicle();
        waterVehicle.run("轮船");
        RoaldVehicle roaldVehicle = new RoaldVehicle();
        roaldVehicle.run("汽车");
    }

    static class AriVehicle {
        public void run(String name) {
            System.out.println(name + "正在 天空中飞行");
        }

    }

    static class WaterVehicle {
        public void run(String name) {
            System.out.println(name + "正在 水中前进");
        }
    }

    static class RoaldVehicle {
        public void run(String name) {
            System.out.println(name + " 正在陆地上跑");
        }
    }
}
