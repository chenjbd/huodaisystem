package com.cc.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(
        scanBasePackages = {"com.cc.app","mapping"})
@MapperScan({"com.cc.app.*.dao"})
@EnableAsync
public class BootApplication extends SpringBootServletInitializer {//支持war包

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(BootApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(BootApplication.class, args);
    }

}
