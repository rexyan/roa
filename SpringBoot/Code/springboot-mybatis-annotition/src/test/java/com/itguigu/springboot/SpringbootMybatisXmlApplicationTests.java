package com.itguigu.springboot;

import javax.sql.DataSource;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootMybatisXmlApplicationTests {

	@Autowired
	DataSource dataSource;
		
	@Test
	void testDataSource() {
		// 获取默认的 DataSource 的驱动
		System.out.println(dataSource.getClass()); // class com.zaxxer.hikari.HikariDataSource
	}
}
