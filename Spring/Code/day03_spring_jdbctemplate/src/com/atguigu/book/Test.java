package com.atguigu.book;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.book.controller.BookController;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book.xml");
		BookController bookController = applicationContext.getBean("bookController", BookController.class);
		// bookController.buyBook();
		
		bookController.checkout();
	}
}
