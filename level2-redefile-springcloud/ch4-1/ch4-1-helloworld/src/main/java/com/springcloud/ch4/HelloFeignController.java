package com.springcloud.ch4;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author mofei
 * @version 2020/10/21 19:55
 */
@RestController
public class HelloFeignController {
    @Autowired
    private HelloWorldClient helloWorldClient;

    @GetMapping
    public String searchGithubRepobyStr(@RequestParam("str") String str) {
        return helloWorldClient.helloWorld(str);
    }
}
