package com.atguigu.book.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.atguigu.book.dao.BookDao;
import com.atguigu.book.service.BookService;

@Service
public class BookServiceImpl implements BookService{
	@Autowired
	private BookDao bookDao;
	
	/**
	 * @Transactional 
	 * 在方法上使用时，只对方法有效果
	 * 在类上使用时候，对类中的所有方法都有效果
	 */
	@Transactional(noRollbackFor = {NullPointerException.class} ,rollbackFor = {RuntimeException.class})
	public void buyBook(String bid, String uid) {
		// 查询图书价格
		Integer bookPrice = bookDao.selectPrice(bid);
		// 减库存
		bookDao.updateSt(bid);
		// 减用于余额
		bookDao.updateBlance(uid, bookPrice);
	}
}
