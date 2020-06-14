package com.yanrs.mpbdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @MapperScan("com.yanrs.mpbdemo.mapper") 可以将扫描放到 mybatis plus 的配置文件里面去
@SpringBootApplication
public class MpbDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MpbDemoApplication.class, args);
    }

}
