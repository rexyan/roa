package com.atguigu.book.dao.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.atguigu.book.dao.BookDao;

@Repository
public class BookDaoImpl implements BookDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public Integer selectPrice(String bid) {
		Integer price= jdbcTemplate.queryForObject("select price from book where bid=?", new Object[] {bid}, Integer.class);
		return price;
	}

	@Override
	public void updateSt(String bid) {
		// 先获取书籍的库存, 如果库存够则卖出一本
		 Integer st = jdbcTemplate.queryForObject("select st from stock where sid = ?", new Object[] {bid}, Integer.class);
		 if (st < 0) {
			 throw new RuntimeException("库存不足");
		 }else {
			 jdbcTemplate.update("update stock set st=st-1 where sid=?", bid);
		 }
	}

	@Override
	public void updateBlance(String uid, Integer price) {
		// 先获取用户余额
		Integer blance = jdbcTemplate.queryForObject("select blance from money where uid = ?", new Object[] {uid}, Integer.class);
		if (blance < price) {
			 throw new RuntimeException("余额不足");
		 }else {
			 jdbcTemplate.update("update money set blance = blance - ? where uid = ?", price, uid);
		 }
	}
}
