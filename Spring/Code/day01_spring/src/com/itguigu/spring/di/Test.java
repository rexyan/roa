package com.itguigu.spring.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans-di.xml");
		
		// 验证 set 方法赋值
		Student student1 = applicationContext.getBean("s1", Student.class);
		System.out.println(student1); 
		
		// 验证构造方法赋值
		Student student2 = applicationContext.getBean("s2", Student.class);
		System.out.println(student2); 
		
		// 验证构造方法有多个存在可能混淆的时候，采用 index + type 进行赋值
		Student student3 = applicationContext.getBean("s3", Student.class);
		System.out.println(student3); 
		
		// 验证 P 命名空间
		Student student4 = applicationContext.getBean("s4", Student.class);
		System.out.println(student4); 
		
		// 验证 ref
		Student student5 = applicationContext.getBean("s5", Student.class);
		System.out.println(student5); 
		
		// 验证内部 bean
		Student student6 = applicationContext.getBean("s6", Student.class);
		System.out.println(student6); 
		
		// 为 List 集合赋值
		Teacher teacher = applicationContext.getBean("t1", Teacher.class);
		System.out.println(teacher); 
		
		// 为 List 集合赋值
		Teacher teacher2 = applicationContext.getBean("t2", Teacher.class);
		System.out.println(teacher2); 
		
		// 为 Map 集合赋值
		Teacher teacher3 = applicationContext.getBean("t3", Teacher.class);
		System.out.println(teacher3); 
		
		// 集合类型的bean
		Teacher teacher4 = applicationContext.getBean("t4", Teacher.class);
		System.out.println(teacher4); 
	}
}
