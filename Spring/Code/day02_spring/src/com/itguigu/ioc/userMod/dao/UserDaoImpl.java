package com.itguigu.ioc.userMod.dao;

import org.springframework.stereotype.Repository;

@Repository
public class UserDaoImpl implements UserDao{

	public UserDaoImpl() {
		System.out.println("UserDaoImpl");
	}

	@Override
	public void adduser() {
		System.out.println("添加成功！");
	}
}
