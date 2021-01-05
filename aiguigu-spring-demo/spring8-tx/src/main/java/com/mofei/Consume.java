package com.mofei;

import java.util.List;

/**
 * @author mofei
 * @version 2020/9/11 9:38
 */
public interface Consume {
    int push(String username, List<String> isbns);
}
