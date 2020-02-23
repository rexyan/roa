package com.atguigu.book_xml.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.book_xml.dao.BookDao;
import com.atguigu.book_xml.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;

	public void buyBook(String bid, String uid) {
		// 查询图书价格
		Integer bookPrice = bookDao.selectPrice(bid);
		// 减库存
		bookDao.updateSt(bid);
		// 减用于余额
		bookDao.updateBlance(uid, bookPrice);
	}
}
