package com.mofei.jvm;

import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * https://zhuanlan.zhihu.com/p/98593407
 * 每个停车场入口都有一个提示牌，上面显示着停车场的剩余车位还有多少，当剩余车位为0时，不允许车辆进入停车场，直到停车场里面有车离开停车场，这时提示牌上会显示新的剩余车位数。
 * 1、停车场容纳总停车量10。
 * <p>
 * 2、当一辆车进入停车场后，显示牌的剩余车位数响应的减1.
 * <p>
 * 3、每有一辆车驶出停车场后，显示牌的剩余车位数响应的加1。
 * <p>
 * 4、停车场剩余车位不足时，车辆只能在外面等待。
 *
 * @author mofei
 * @since 2021/3/4 15:02
 */
public class SemaphoreDemo {
    public static void main(String[] args) {

    }


}

class Pool {
    private int MaxAvailable = 10;
    Semaphore available = new Semaphore(MaxAvailable);

    //获取对象
    public Object getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    //可以将一个对象列表转成一个数组
    protected String[] items;
    protected boolean[] useds = new boolean[MaxAvailable];


    public synchronized void initItems(List<String> items) {
        this.items = items.toArray(new String[0]);
    }

    private synchronized Object getNextAvailableItem() {
        //遍历一个Boolean数组， 如果有一个不是true ，那么设置为true ， 返回这个i对应的ITEM
        for (int i = 0; i < useds.length; i++) {
            if (!useds[i]) {
                useds[i] = true;
                return items[i];
            }
        }
        return null;
    }

    //设置对象
    public void putItem(Object o) {
        if (markUnUsed(o)) {
            available.release();
        }
    }

    private synchronized boolean markUnUsed(Object o) {
        for (int i = 0; i < MaxAvailable; i++) {
            if (o == items[i]) {
                if (useds[i]) {
                    useds[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }
}