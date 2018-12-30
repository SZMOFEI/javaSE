package com.mofei.day08.api.classes;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mofei
 * @date 2018/12/23 22:54
 */
public class Master extends User {
    public Master(String name, Integer money) {
        super(name, money);
    }

    public Master() {
    }

    public List<Integer> send(Integer total , Integer count){
        ArrayList<Integer> integers = new ArrayList<Integer>();
        System.out.println(" 开始发红包了" );
        if (total>super.getMoney()){
            System.out.println("账户余额不足,请充值后再发红包");
            return  integers;
        }
        super.setMoney(super.getMoney()-total);
        if (count==null||count<=0){
            count =1;
        }
        int perMoney = total / count;
        int mod = total%count;
        for (int i = 0; i < total-1; i++) {
            integers.add(perMoney);
        }
        int last = perMoney +mod;
        integers.add(last);
        return integers;
    }

    public void show() {
        toString();
    }

}
