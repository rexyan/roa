package com.itguigu.zcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// Feign
@EnableFeignClients 
// 注册到注册中心
@EnableDiscoveryClient
// 熔断
@EnableCircuitBreaker
@SpringBootApplication
public class ZcwWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZcwWebApplication.class, args);
	}

}
