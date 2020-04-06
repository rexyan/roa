package com.itguigu.com.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


// @ComponentScan("com.itguigu.com.springboot") 默认就会扫描主程序下面的子包，所以可以不加
@SpringBootApplication
public class SpringbootHelloAutoCreateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootHelloAutoCreateApplication.class, args);
	}

}
