package com.atguigu.spring.aopxml;

import org.springframework.stereotype.Component;

@Component
public class MyLogger {
	public void before() {
		System.out.println("前置通知");
	}
}
