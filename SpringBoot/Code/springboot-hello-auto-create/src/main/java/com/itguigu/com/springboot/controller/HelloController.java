package com.itguigu.com.springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
	@ResponseBody
	@GetMapping("/hello")
	public String hello(){
	return "OK!+哈哈";
	}
}
