package com.auguigu.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestInterceptorController {
	
	@ResponseBody
	@RequestMapping(value = "/testInterceptor")
	public String testInterceptor() {
		return "success";
	}
}
