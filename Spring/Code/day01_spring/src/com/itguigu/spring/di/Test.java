package com.itguigu.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-di.xml");
		
		// 验证 set 方法赋值
		Student student1 = applicationContext.getBean("s1", Student.class);
		System.out.println(student1); // Student [id=1001, name=zhangsan, age=23, sex=男, score=null]
		
		// 验证构造方法赋值
		Student student2 = applicationContext.getBean("s2", Student.class);
		System.out.println(student2); // Student [id=10086, name=lisi, age=45, sex=女, score=null]
		
		// 验证构造方法有多个存在可能混淆的时候，采用 index + type 进行赋值
		Student student3 = applicationContext.getBean("s3", Student.class);
		System.out.println(student3); // Student [id=10022, name=wangwu, age=null, sex=女, score=90.0]
	}
}
