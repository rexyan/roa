package com.itguigu.zcw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableTransactionManagement
@EnableDiscoveryClient
@EnableSwagger2
@SpringBootApplication
public class ZcwProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZcwProjectApplication.class, args);
	}

}
