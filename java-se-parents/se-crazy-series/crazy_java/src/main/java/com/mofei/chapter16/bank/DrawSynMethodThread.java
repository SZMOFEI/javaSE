package com.mofei.chapter16.bank;

/**
 * @Classname DrawThread
 * @Description TODO
 * @Date 2019/2/15 10:05
 * @Created by Administrator
 */
public class DrawSynMethodThread extends Thread {
    //取钱的账号
    private Account account;
    //取钱的数额
    private Double drawAmount;

    public DrawSynMethodThread(String name, Account account, Double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
       account.draw(drawAmount);
    }

    public static void main(String[] args) {
        Account account = new Account("12345678", 1000D);
        DrawSynMethodThread t1 = new DrawSynMethodThread("第一个人", account, 800D);
        t1.start();
        DrawSynMethodThread t2 = new DrawSynMethodThread("第二个人", account, 800D);
        t2.start();

    }
}
