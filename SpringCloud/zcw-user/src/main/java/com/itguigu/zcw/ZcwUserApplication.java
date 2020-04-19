package com.itguigu.zcw;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement // 开启事务管理
@MapperScan("com.itguigu.zcw.user.mapper")
@EnableDiscoveryClient // 开启服务注册发现服务
@SpringBootApplication
public class ZcwUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZcwUserApplication.class, args);
	}

}
