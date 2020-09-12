package com.mofei;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author mofei
 * @date 2020/9/11 9:42
 */
@Service
public class ConsumeImpl implements Consume {
    @Autowired
    private BookService bookService;

    /**
     *
     * @param username 用户的名称
     * @param isbns    编号
     * @return int
     */
//    @Transactional
    @Override
    public int push(String username, List<String> isbns) {
        for (String isbn : isbns) {
            int i = bookService.processOder(username, isbn);
        }
        return 1;
    }
}
