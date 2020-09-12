package com.mofei;

import com.mofei.exception.BalanceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author mofei
 * @date 2020/9/10 21:51
 */
@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    /**
     * * 事务的传播特性 : 默认是的Required
     * * 1. Propagation propagation() default Propagation.REQUIRED; 默认， 这种情况的话就是有事务就使用事务， 一起成功，一起失败，也就是往下子事务传递。
     * Support a current transaction, create a new one if none exists.
     * * 2. Propagation.REQUIRES_NEW 这种情况下就是每个子事务会单独起一个事务， 单独成功， 主事务挂起  。 主事务回滚， 子事务不会回滚
     * 3.Propagation.SUPPORTS 支持事务  如果上层有事务，和reqired一样， 如果上层没有， 那么就是回滚最小的，2个扣库存成功和扣款成功
     *
     * @param username 用户名称
     * @param isbn     编号
     * @return int
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = BalanceException.class, readOnly = false, timeout = 3)
    @Override
    public int processOder(String username, String isbn) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //根据isbn查询价格
        String price = bookDao.getPriceByIsbn(isbn);

        //扣减库存
        int i = bookDao.subStock(isbn);

        int i1 = bookDao.subBalance(username, price);
        //扣减余额
        return i1;
    }
}
