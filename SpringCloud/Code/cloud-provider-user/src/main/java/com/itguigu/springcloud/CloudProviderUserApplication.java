package com.itguigu.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;


@EnableDiscoveryClient
@SpringBootApplication
public class CloudProviderUserApplication {
	
	/**
	 * 配置 RestTemplate bean 对象。因为这里主程序是一个配置类，所以就写在这里（并不是非得写到这里，只要是配置类就行）
	 * @return
	 */
	@Bean
	@LoadBalanced // 负载均衡
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(CloudProviderUserApplication.class, args);
	}

}
