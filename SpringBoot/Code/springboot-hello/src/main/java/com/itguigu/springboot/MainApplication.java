package com.itguigu.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// 声明当前项目为 SpringBoot 项目
@SpringBootApplication 
public class MainApplication {
	public static void main(String[] args) {
		// 启动内置的 Tomcat
		SpringApplication.run(MainApplication.class, args);
	}
}
