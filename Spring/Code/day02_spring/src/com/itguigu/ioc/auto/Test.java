package com.itguigu.ioc.auto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("auto.xml");
		Emp emp = applicationContext.getBean("emp", Emp.class);
		System.out.println(emp);
	}
}
