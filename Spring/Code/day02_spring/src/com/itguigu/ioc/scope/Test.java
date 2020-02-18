package com.itguigu.ioc.scope;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("scope.xml");
		Student student = applicationContext.getBean("student", Student.class);
		System.out.println(student);
		
		Student student2 = applicationContext.getBean("student", Student.class);
		System.out.println(student2);
		
		System.out.println(student == student2); // true, 说明是单例模式
	}
}
