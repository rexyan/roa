package com.itguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableHystrixDashboard // 开启可视化监控
@EnableCircuitBreaker // 开启断路保护功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CloudProviderUserApplication {
	public static void main(String[] args) {
		SpringApplication.run(CloudProviderUserApplication.class, args);
	}
}
