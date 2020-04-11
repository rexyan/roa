package com.itguigu.zcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer // 开启注册中心功能
@SpringBootApplication
public class ZcwRegisterApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZcwRegisterApplication.class, args);
	}
}
