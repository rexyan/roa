package com.itguigu.ioc.life;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext("life.xml");
		Person person = applicationContext.getBean("person", Person.class);
		System.out.println(person); // 打印对象，调用 toString，表示对象可以用了
		applicationContext.close(); // close 才会调用 bean 的销毁方法， ApplicationContext 没有 close 方法，所有使用 ClassPathXmlApplicationContext
		
		/**
		 *  bean的生命周期-1: 通过构造器或工厂方法创建 bean 实例
			bean的生命周期-2: 为bean的属性设置值
			bean的生命周期-3: 初始化方法
			bean的生命周期-4: 使用 bean
			Person [id=10001, sex=男, name=张三]
			bean的生命周期-5: 调用bean的销毁方法
		 */
		
		/**
		 *  bean的生命周期-1: 通过构造器或工厂方法创建 bean 实例
			bean的生命周期-2: 为bean的属性设置值
			bean的生命周期-3: 初始化方法
			bean的生命周期-4: 使用 bean
			Person [id=10001, sex=男, name=这是我在后置处理器中修改的名字]
			bean的生命周期-5: 调用bean的销毁方法
		 */
	}
}
