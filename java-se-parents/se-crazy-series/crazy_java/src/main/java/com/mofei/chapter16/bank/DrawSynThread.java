package com.mofei.chapter16.bank;

/**
 * @Classname DrawThread
 * @Description TODO
 * @Date 2019/2/15 10:05
 * @Created by Administrator
 */
public class DrawSynThread extends Thread {
    //取钱的账号
    private Account account;
    //取钱的数额
    private Double drawAmount;

    public DrawSynThread(String name, Account account, Double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    @Override
    public void run() {
        synchronized (account) {
            //判断余额大小
            if (account.getBlance() >= drawAmount) {
                System.out.println(getName()+" 取钱"+drawAmount+"成功！ 账户:"+account.getAccountNo()+ " 还有余额"+account.getBlance());
                try {
                    sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //扣除余额
                account.setBlance(account.getBlance() - drawAmount);
                System.out.println(account.getAccountNo() + " 账户余额剩余：" + account.getBlance());
            }else {
                System.out.println(getName()+" 取钱"+drawAmount+"失败！ 账户:"+account.getAccountNo()+ " 还有余额"+account.getBlance());
            }
        }
    }

    public static void main(String[] args) {
        Account account = new Account("12345678", 1000D);
        DrawSynThread t1 = new DrawSynThread("第一个人", account, 800D);
        t1.start();
        DrawSynThread t2 = new DrawSynThread("第二个人", account, 800D);
        t2.start();

    }
}
