package com.atguigu.controller;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.atguigu.bean.User;

@Controller
public class TestController {
	
	@ResponseBody
	@RequestMapping("/testListaner")
	public String testListaner(HttpSession session) {
		/**
		// 从 HttpSession 中获取 ServletContext 对象 
		ServletContext servletContext = session.getServletContext();
		// 从 ServletContext 中获取 Spring IOC 对象（我们在监听器中将 Spring IOC 对象存放在了 ServletContext ）
		ApplicationContext applicationContext = (ApplicationContext) servletContext.getAttribute("applicationContext");
		// 从 Spring IOC 中获取 Spring 管理的 user 对象
		User user = applicationContext.getBean("user", User.class);
		**/
		return "success";
	}
}
