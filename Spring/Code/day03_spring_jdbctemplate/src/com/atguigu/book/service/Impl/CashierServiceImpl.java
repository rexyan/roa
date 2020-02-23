package com.atguigu.book.service.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.book.service.BookService;
import com.atguigu.book.service.Cashier;

@Service
@Transactional
public class CashierServiceImpl implements Cashier{
	
	@Autowired
	private BookService bookService;
	
	@Override
	public void checkOut(String uid, List<String> bids) {
		for (String bid : bids) {
			bookService.buyBook(bid, uid);
		}
	}

}
