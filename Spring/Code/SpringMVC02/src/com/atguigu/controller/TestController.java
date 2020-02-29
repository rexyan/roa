package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/mvc")
public class TestController {
	@RequestMapping(value = "/test/{id}/{username}", method = RequestMethod.GET)
	public String testGET(@PathVariable("id") Integer id, @PathVariable("username") String username) {
		System.out.println("id:" + id + ", username:" + username);
		return "success";
	} 
}
