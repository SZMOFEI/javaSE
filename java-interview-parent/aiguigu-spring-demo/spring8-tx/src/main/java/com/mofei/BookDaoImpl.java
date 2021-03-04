package com.mofei;

import com.mofei.exception.BalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * @author mofei
 * @version 2020/9/10 20:36
 */
@Repository("bookDaoImpl")
public class BookDaoImpl implements BookDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public String getPriceByIsbn(String isbn) {
        String sql = "select price from book where isbn = ?";
        String price = jdbcTemplate.queryForObject(sql, String.class, isbn);
        return price;
    }

    @Override
    public int subStock(String isbn) {
        int stock = jdbcTemplate.queryForObject("select stock from  stock where isbn = ? ", int.class, isbn);
        if (stock <= 0) {
            throw new StockException("扣款库存不足");
        }
        String sql = "update stock set stock = stock -1 where isbn = ?";
        int update = jdbcTemplate.update(sql, isbn);
        return update;
    }


    @Override
    public int subBalance(String username, String price) {
        String balanceByUsername = getBalanceByUsername(username);
        // 2 compareTo  1  会得到  1 即大于0  余额不足
        if (Integer.parseInt(balanceByUsername)<Integer.parseInt(price)) {
            throw new BalanceException("扣款余额不足");
        }
        String sql = "update account set balance = balance -? where username = ?";
        int update = jdbcTemplate.update(sql, price, username);
        return update;
    }

    @Override
    public String getBalanceByUsername(String username) {
        String sql = "select balance from account where username = ?";
        String price = jdbcTemplate.queryForObject(sql, String.class, username);
        return price;
    }

}
