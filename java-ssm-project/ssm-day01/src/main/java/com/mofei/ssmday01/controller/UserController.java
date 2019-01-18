package com.mofei.ssmday01.controller;

import com.mofei.ssmday01.entity.User;
import com.mofei.ssmday01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author mofei
 * @date 2019/1/15 22:00
 */
@RequestMapping("/test")
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/list")
    public List<User> list(){
        return userService.selectAllUser();
    }
    @RequestMapping("/add")
    public int add(@RequestBody User user ){
        return userService.insert(user);
    }
}
