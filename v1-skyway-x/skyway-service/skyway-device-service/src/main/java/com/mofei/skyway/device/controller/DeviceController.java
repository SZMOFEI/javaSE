package com.mofei.skyway.device.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mofei.skyway.device.entity.SkywayDevice;
import com.mofei.skyway.device.service.SkywayDeviceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mofei
 * @since 2021/1/2 18:24
 */
@RestController
@RequestMapping(value = "/admin/device")
@Api(tags = "OSS-DEVICE", value = "OSS-DEVICE")
public class DeviceController {
    @Autowired
    private SkywayDeviceService skywayDeviceService;

    @GetMapping("/page")
    @ApiOperation(value = "获取设备分页的接口")
    public Page<SkywayDevice> pageAll() {
        Page page = skywayDeviceService.page();
        return page;
    }
}
