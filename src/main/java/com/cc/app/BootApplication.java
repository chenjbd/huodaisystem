package com.cc.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(
        scanBasePackages = {"com.cc.app","mapping"},
        exclude = {org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
        })//禁用activiti登录验证
@MapperScan({"com.cc.app.*.dao"})
@EnableAsync
public class BootApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
