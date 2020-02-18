package com.itguigu.proxy;

public class MyLogger {
	public static void before(String methodName, String args) {
		System.out.println("方法 - " + methodName + " args:" + args);
	}
	
	public static void after(String methodName, Object result) {
		System.out.println("方法 - " + methodName + " result:" + result);
	}
}
