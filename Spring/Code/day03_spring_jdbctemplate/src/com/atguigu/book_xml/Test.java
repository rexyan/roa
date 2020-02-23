package com.atguigu.book_xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.atguigu.book_xml.controller.BookController;

public class Test {
	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("book_xml.xml");
		BookController bookController = applicationContext.getBean("bookController", BookController.class);
		bookController.buyBook();
		
		// bookController.checkout();
	}
}
