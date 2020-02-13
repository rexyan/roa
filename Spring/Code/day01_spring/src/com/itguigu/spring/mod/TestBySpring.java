package com.itguigu.spring.mod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBySpring {
	public static void main(String[] args) {
		// 获取Spring 所管理的 bean
		// 1. 根据xml文件初始化容器
		ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 2. 获取对象
		Person person = (Person) aContext.getBean("person");
		System.out.println(person); // Person [id=1001, name=zhangsan]
	}
}
