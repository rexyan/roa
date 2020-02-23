package com.atguigu.book_xml.dao;

public interface BookDao {
	Integer selectPrice(String bid);
	
	void updateSt(String bid);
	
	void updateBlance(String uid, Integer price);
}
