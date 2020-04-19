package com.itguigu.zcw.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DispatcherController {
	
	/**
	 * 首页
	 * @return
	 */
	@GetMapping("/index")
	public String index() {
		return "index";
	}
	
	/**
	 * 登录页
	 * @return
	 */
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	
	/**
	 * 注册页
	 * @return
	 */
	@GetMapping("/register")
	public String register() {
		return "register";
	}
}
