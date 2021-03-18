package com.mofei.skyway.user.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mofei.skyway.user.entity.SkywayUser;
import com.mofei.skyway.user.mapper.SkywayUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author mofei
 * @since 2021/1/4 16:42
 */
@Service
public class SkywayUserService {
    @Autowired
    private SkywayUserMapper userMapper;

    public Page<SkywayUser> queryPage() {
        return userMapper.selectPage(new Page<>(), new QueryWrapper<>());
    }
}
