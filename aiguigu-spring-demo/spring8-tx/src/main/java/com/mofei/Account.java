package com.mofei;

/**
 * @author mofei
 * @date 2020/9/10 20:35
 */
public class Account {
    private String username;
    private Integer balance;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public Account() {
    }

    public Account(String username, Integer balance) {
        this.username = username;
        this.balance = balance;
    }
}
