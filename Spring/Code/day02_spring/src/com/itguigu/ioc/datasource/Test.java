package com.itguigu.ioc.datasource;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class Test {
	public static void main(String[] args) throws Exception {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("datasource.xml");
		DruidDataSource dataSource = applicationContext.getBean("datasource", DruidDataSource.class);
		System.out.println(dataSource);
		
		DruidPooledConnection connection = dataSource.getConnection();
		System.out.println(connection);
	}
}	
