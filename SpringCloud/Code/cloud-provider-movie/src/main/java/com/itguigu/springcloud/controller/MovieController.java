package com.itguigu.springcloud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.itguigu.springcloud.bean.Movie;
import com.itguigu.springcloud.service.MovieService;

// 这里使用 @RestController 来替代 @Controller 和 @ResponseBody
@RestController

// @Controller
public class MovieController {
	
	@Autowired
	MovieService moviceService;
	
	// 获取当前配置的端口信息，用来判断那个 Movie 服务被调用
	@Value("${server.port}")
	int port;
	
	// @ResponseBody
	@GetMapping("/getMovieById/{id}")
	public Movie getMovieById(@PathVariable("id") Integer id) {
		System.out.println("port: " + port);
		return moviceService.getMovieById(id);
	}
}
