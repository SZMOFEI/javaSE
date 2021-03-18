package com.mofei.skyway.device.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mofei.skyway.device.entity.SkywayDevice;
import com.mofei.skyway.device.mapper.SkywayDeviceMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author mofei
 * @version 2020/12/29 20:21
 */
@Service
public class SkywayDeviceService {
    @Resource
    private SkywayDeviceMapper skywayDeviceMapper;

    public SkywayDevice getById(String id) {
        return skywayDeviceMapper.selectById(id);
    }

    public Page page() {
        return skywayDeviceMapper.selectPage(new Page(), new QueryWrapper<>());
    }
}
