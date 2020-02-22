package com.atguigu.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("aop.xml");
		// 这里获取的对象需要转换为 MathI，原因和直接介绍 aop 的时候一样，如果是 mathImpl 就是兄弟关系了
		MathI mathI = applicationContext.getBean("mathImpl", MathI.class);
		int add = mathI.add(1, 1);
		System.out.println(add);
		
		int sub = mathI.sub(1, 1);
		System.out.println(sub);
		
		// 测试切入点表达式
		TestHandler bean = applicationContext.getBean("testHandler", TestHandler.class);
		bean.test();
		/** 
		 * method: test ,args: []
		   前置通知
		   测试切入点表达式
		 */
		
		mathI.div(4, 1);
	}
}
