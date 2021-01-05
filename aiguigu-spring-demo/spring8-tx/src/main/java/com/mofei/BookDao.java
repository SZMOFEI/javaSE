package com.mofei;

/**
 * @author mofei
 * @version 2020/9/10 20:36
 */
public interface BookDao {
    String getPriceByIsbn(String isbn);

    int subStock(String isbn);

    int subBalance(String username,String price);

    String getBalanceByUsername(String username);
}
