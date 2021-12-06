package com.example.rlyservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@EnableDiscoveryClient
@SpringBootApplication
@ComponentScan(basePackages = "com.example")    //可以扫描到common模块中的配置类
public class WleafApplication {
    public static void main(String[] args) {
        SpringApplication.run(WleafApplication.class, args);
    }
}
