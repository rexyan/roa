package com.atguigu.spring.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TestAspectOrder {
	@Before(value = "execution(* com.atguigu.spring.aop.*.*(..))")
	public void befordMethod() {
		System.out.println("TestAspectOrder - 前置通知");
	}
}
