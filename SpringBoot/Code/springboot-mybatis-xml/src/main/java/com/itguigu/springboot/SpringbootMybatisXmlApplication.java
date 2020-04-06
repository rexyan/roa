package com.itguigu.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

// 启用声明式事务
@EnableTransactionManagement
// 扫描 mapper 
@MapperScan("com.itguigu.springboot.mapper")
@SpringBootApplication
public class SpringbootMybatisXmlApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringbootMybatisXmlApplication.class, args);
	}
}
