package com.itguigu.zcw.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class TMemberController {
	
	@GetMapping("/member/order")
	public String memberOrder() {
		
		return "member/zc";
	}
}
