package com.itguigu.ioc.userMod.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itguigu.ioc.userMod.dao.UserDao;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired(required = false)
	private UserDao userDao;
	
	public UserServiceImpl() {
		System.out.println("UserServiceImpl");
	}

	@Override
	public void addUser() {
		userDao.adduser();
	}
	
}
