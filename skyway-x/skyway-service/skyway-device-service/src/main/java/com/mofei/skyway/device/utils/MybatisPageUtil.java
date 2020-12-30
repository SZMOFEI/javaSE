package com.mofei.skyway.device.utils;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

/**
 * @author mofei
 * @date 2020/12/29 19:38
 */
public class MybatisPageUtil<T> {
    public static <T>Page getNewPage(int pageNum,int pageSize){
        return new Page<T>(pageNum,pageSize);
    }

    public static <T>Page getNewPage(){
        return new Page<T>();
    }

    public static <T>QueryWrapper getNewQueryWrapper() {
        return new QueryWrapper<T>();
    }
}
