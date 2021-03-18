package com.example.apollo.quickstart.config;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigService;

/**
 * @author com.mofei
 * @version 2020/3/18 8:37
 */
public class GetConfigTest {
    public static void main(String[] args) throws InterruptedException {
        while (true) {
            Thread.sleep(1000);
            Config rocknamespace = ConfigService.getConfig("rocknamespace");
//            Config appConfig = ConfigService.getAppConfig("rocknamespace");
            String property = rocknamespace.getProperty("MYSQL_HOST", "127.0.127.0");
            String redis_password = rocknamespace.getProperty("MYSQL_PASSWORD", "127.0.127.0");
            System.out.println("property = " + property);
            System.out.println("MYSQL_PASSWORD = " + redis_password);
        }
    }
}
