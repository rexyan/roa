package com.itguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// 将该服务注册到注册中心（即启用服务注册和发现功能）
@EnableDiscoveryClient
@SpringBootApplication
public class CloudProviderMovieApplication {

	public static void main(String[] args) {
		SpringApplication.run(CloudProviderMovieApplication.class, args);
	}

}
