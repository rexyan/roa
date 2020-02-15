package com.itguigu.spring.factorybean;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext axApplicationContext = new ClassPathXmlApplicationContext("factory-bean.xml");
		Object bean = axApplicationContext.getBean("factory");
		System.out.println(bean);
	}
}
