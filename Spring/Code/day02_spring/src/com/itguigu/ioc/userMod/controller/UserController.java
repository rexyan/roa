package com.itguigu.ioc.userMod.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.itguigu.ioc.userMod.service.UserService;

@Controller
public class UserController {
	
	@Autowired(required = false)
	private UserService userService;
	
	public void addUser() {
		userService.addUser();
	}

	public UserController() {
		System.out.println("UserController");
	}
}
