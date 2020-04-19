package com.itguigu.zcw.controller;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpringSessionController {
	@GetMapping("/get")
	public String get(HttpSession session) {
		// 使用 spring session 后 HttpSession 不再是以前的 HttpSession，而是被重写了
		String attributeStr = (String) session.getAttribute("test");
		System.out.println(session.getClass());
		// org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper$HttpSessionWrapper
		return attributeStr; 
	}
	
	@GetMapping("/set")
	public String set(HttpSession session) {
		// 使用 spring session 后 HttpSession 不再是以前的 HttpSession，而是被重写了
		session.setAttribute("test", "test spring session");
		System.out.println(session.getClass());
		// org.springframework.session.web.http.SessionRepositoryFilter$SessionRepositoryRequestWrapper$HttpSessionWrapper
		return "ok";
	}
}
