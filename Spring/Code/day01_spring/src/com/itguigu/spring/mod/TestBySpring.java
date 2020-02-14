package com.itguigu.spring.mod;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBySpring {
	public static void main(String[] args) {
		// 获取Spring 所管理的 bean
		// 1. 根据xml文件初始化容器
		ApplicationContext aContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		// 2. 获取对象(通过 id)
		Person person = (Person) aContext.getBean("person");
		System.out.println(person);
		
		// 获取对象(通过 类名.class, 使用此方法获取对象时候，要求 Spring 所管理的此类型的对象只有一个)
		// Person person2 = aContext.getBean(Person.class);
		// System.out.println(person);
		
		// 获取对象(id + 类型)
		Person person3 = aContext.getBean("person", Person.class);
		System.out.println(person);
	}
}
