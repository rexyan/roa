package com.atguigu.book.service;

import java.util.List;

public interface Cashier {
	void checkOut(String uid, List<String> bids);
}
