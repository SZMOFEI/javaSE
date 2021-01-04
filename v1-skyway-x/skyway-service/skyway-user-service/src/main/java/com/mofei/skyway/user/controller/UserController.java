package com.mofei.skyway.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mofei.skyway.user.entity.SkywayUser;
import com.mofei.skyway.user.service.SkywayUserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mofei
 * @since 2021/1/4 16:37
 */
@RestController
@RequestMapping(value = "/admin/user")
public class UserController {

    @Autowired
    private SkywayUserService skywayUserService;

    @GetMapping("/page")
    @ApiOperation(value = "用户分页查询")
    public Page<SkywayUser> page(){
        return skywayUserService.queryPage();
    }
}
