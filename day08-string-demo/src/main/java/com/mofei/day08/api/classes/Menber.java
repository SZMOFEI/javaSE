package com.mofei.day08.api.classes;

import java.util.List;
import java.util.Random;

/**
 * @author mofei
 * @date 2018/12/23 22:59
 */
public class Menber extends User{
    public Menber(String name, Integer money) {
        super(name, money);
    }

    public Menber() {
        super();
    }

    public void received(List<Integer> total) {
        int index = new Random().nextInt(total.size());
        Integer deledata = total.remove(index);
        Integer left = super.getMoney();

        super.setMoney(deledata+left);
    }
}
