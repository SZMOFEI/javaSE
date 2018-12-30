package com.mofei.pattern;

/**
 * @author mofei
 * @date 2018/11/8 23:21
 */
public abstract class PrepareYinliaoTemplate {
    /**
     * 炮製飲料的模板框架
     */
    public final void prepareYinliaoTemple() {
        //1.把水煮沸
        boilWater();
        //2.泡制
        brew();
        //3.把飲料倒入杯中
        pourInCup();
        //4.加入調料
        if (isCustomerWants()) {
            addCondiment();
        }
    }

    protected boolean isCustomerWants() {
        return true;
    }

    /**
     * 把水煮沸
     */
    private void boilWater() {
        System.out.println("把水煮沸");
    }

    protected abstract void brew();

    private void pourInCup() {
        System.out.println("把飲料倒入杯中");
    }

    protected abstract void addCondiment();


}
