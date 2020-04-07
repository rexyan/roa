package com.itguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

// 声明当前项目为注册中心
@EnableEurekaServer
@SpringBootApplication
public class CloudEurekaRegistryCenterApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudEurekaRegistryCenterApplication.class, args);
	}

}
