package com.springcloud.ch4;

import com.springcloud.ch4.config.HelloWorldClientConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author mofei
 * @version 2020/10/21 19:50
 */
@FeignClient(name = "githut-client", url = "https://api.github.com", configuration = HelloWorldClientConfig.class)
public interface HelloWorldClient {

    @RequestMapping(method = RequestMethod.GET, value = "/search/repositories")
    public String helloWorld(@RequestParam("q") String queryStr);
}
