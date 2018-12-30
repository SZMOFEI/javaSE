package com.mofei.day08.api.classes;

import java.util.List;

/**
 * 需求:
 *      1.群主可以发红包
 *      2.群员可以收红包
 *      群主和成员都具有的属性
 *      群主具有发红包的方法
 *          1.返回值   List<Integer>
 *          2.参数列表  total 总共多少钱,count 多少份
 *          3.方法名    send
 *      群员具有收红包的方法
 *               1.返回值   void
 *  *          2.参数列表  List<Integer>
 *  *          3.方法名   received
 * @author mofei
 * @date 2018/12/23 22:49
 */
public class HongbaoDemo {
    public static void main(String[] args) {
        Master z = new Master("张三", 50);
        Menber z1 = new Menber("群员1", 0);
        Menber z2 = new Menber("群员2", 0);
        show(z);
        show(z1);
        show(z2);

        List<Integer> hongbao = z.send(20, 2);
        z1.received(hongbao);
        z2.received(hongbao);
        show(z);
        show(z1);
        show(z2);
    }

    public static void  show (User user) {
        user.show();
    }

}
