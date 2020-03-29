package com.atguigu.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class GongfuController {
	// 为 /level1/1 设置只有学徒角色和 luohan 的权限才能访问
	@PreAuthorize("hasRole('学徒') AND hasAuthority('luohan')")
	@GetMapping("/level1/1")
	public String leve1Page1(){
		return "/level1/1";
	}
	
	// 为 /level1/2 设置只有学徒角色和 wudang 的权限才能访问
	@PreAuthorize("hasRole('学徒') AND hasAuthority('wudang')")
	@GetMapping("/level1/2")
	public String leve1Page2(){
		return "/level1/2";
	}
	
	// 为 /level1/3 设置只有学徒角色和 quanzhen 的权限才能访问
	@PreAuthorize("hasRole('学徒') AND hasAuthority('quanzhen')")
	@GetMapping("/level1/3")
	public String leve1Page3(){
		return "/level1/3";
	}
	
	@GetMapping("/level2/{path}")
	public String leve2Page(@PathVariable("path")String path){
		return "/level2/"+path;
	}
	
	@GetMapping("/level3/{path}")
	public String leve3Page(@PathVariable("path")String path){
		return "/level3/"+path;
	}

}
