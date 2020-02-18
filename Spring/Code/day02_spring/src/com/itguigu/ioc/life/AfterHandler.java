package com.itguigu.ioc.life;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class AfterHandler implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		// bean 初始化之前
		Person person = (Person) bean;
		person.setName("这是我在后置处理器中修改的名字");
		return person;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// // bean 初始化之后
		return bean;
	}
}
