package com.atguigu.book.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.atguigu.book.service.BookService;
import com.atguigu.book.service.Cashier;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;
	
	@Autowired
	private Cashier cashier;
	
	// 一次买一本
	public void buyBook() {
		bookService.buyBook("1", "1001");
	}
	
	// 一次买多本
	public void checkout() {
		List<String> bids = new ArrayList<String>();
		bids.add("1");
		bids.add("2");
		cashier.checkOut("1001", bids);
	}
}
