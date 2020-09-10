package com.mofei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mofei
 * @date 2020/9/10 21:51
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public int processOder(String username, String isbn) {
        //根据isbn查询价格
        String price = bookDao.getPriceByIsbn(isbn);

        //扣减库存
        int i = bookDao.subStock(isbn);

        int i1 = bookDao.subBalance(username, price);
        //扣减余额
        return i1;
    }
}
