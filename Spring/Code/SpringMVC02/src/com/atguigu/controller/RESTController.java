package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class RESTController {
	
	@RequestMapping("/testREST/{id}")
	public String name(@PathVariable("id") String id) {
		System.out.println("GET, ID=" + id);
		return "success";
	}
	
	@RequestMapping(value = "/testREST", method = {RequestMethod.POST})
	public String name1() {
		System.out.println("POST");
		return "success";
	}
	
	@RequestMapping(value = "/testREST", method = {RequestMethod.PUT})
	public String name2() {
		System.out.println("PUT");
		return "success";
	}
	
	@RequestMapping(value = "/testREST", method = {RequestMethod.DELETE})
	public String name3(@PathVariable("id") String id) {
		System.out.println("DELETE");
		return "success";
	}
}
