package com.itguigu.springcloud.dao;

import org.springframework.stereotype.Repository;

import com.itguigu.springcloud.bean.User;

@Repository
public class UserDao {
	public User getUserById(Integer id) {
		return new User(id, "User" + id);
	}
}
