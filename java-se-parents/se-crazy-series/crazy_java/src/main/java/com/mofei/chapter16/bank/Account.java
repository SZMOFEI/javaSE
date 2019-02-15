package com.mofei.chapter16.bank;

/**
 * @Classname Account
 * @Description TODO
 * @Date 2019/2/15 10:05
 * @Created by Administrator
 */
public class Account {
    private String accountNo;
    private Double blance;

    public Account(String accountNo, Double blance) {
        this.accountNo = accountNo;
        this.blance = blance;
    }

    public Double getBlance() {
        return blance;
    }

    public Account() {

    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public synchronized Double draw(double drawAmount) {
        if (blance>=drawAmount){
            System.out.println(Thread.currentThread().getName()+" 取钱成功！ 取钱：" +drawAmount);
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            blance-=drawAmount;
            System.out.println(this.getAccountNo()+" 余额 ：" +blance);

        }else {
            System.out.println(Thread.currentThread().getName()+" 取钱失败 ，余额不足！");
        }
        return blance;
    }

    public void setBlance(Double blance) {
        this.blance = blance;
    }
}
