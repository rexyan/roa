package com.auguigu.test;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	
	@RequestMapping(value = "testException", method = RequestMethod.POST)
	@ResponseBody
	public String testException() {
		return "success";
	}
	
	@RequestMapping(value = "testException2")
	@ResponseBody
	public String testException2() {
		// 构建一个空指针异常
		String string = null;
		string.substring(1);
		return "seccess";
	}
}
