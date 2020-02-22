package com.atguigu.spring.aopxml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop_xml.xml");
		MathI mathI = applicationContext.getBean("mathImpl", MathI.class);
		int result = mathI.add(1, 1);
		System.out.println(result);
		/**
		 * 前置通知
			2
		 */
	}
}
