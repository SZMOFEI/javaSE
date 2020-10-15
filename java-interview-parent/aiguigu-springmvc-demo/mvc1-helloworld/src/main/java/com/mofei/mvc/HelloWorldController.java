package com.mofei.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author mofei
 * @date 2020/9/13 5:10
 */
@Controller
public class HelloWorldController {

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    public String helloWorld() {
        System.out.println("执行了hello world ");
        return "hello";
    }
}
