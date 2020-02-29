package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class TestController {
	/**
	 * View 视图的类型：
	 * 1. InternalResourceView: 转发视图
	 * 2. JstlView: 转发视图，用来支持页面中的 jstl 的。继承自 InternalResourceView
	 * 3. RedirectView: 重定向视图 
	 * 
	 * View 的作用: 处理模型数据和页面跳转（转发, 重定向）
	 */
	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public String testGET(String username) {
		System.out.println("username:" + username);
		return "success";
		// return "redirect:/index.jsp" 重定向，使用的是重定向视图
	} 
}
