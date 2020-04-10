package com.itguigu.springcloud.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.itguigu.springcloud.bean.Movie;
import com.itguigu.springcloud.bean.User;
import com.itguigu.springcloud.service.MovieServiceFeign;
import com.itguigu.springcloud.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	MovieServiceFeign moviceServiceFeign;
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	@GetMapping("/getUserById/{id}")
	public User getUserById(@PathVariable("id") Integer id) {
		return userService.getUserById(id);
	}
	
	/**
	 * 购买电影票
	 * @param userId
	 * @param movieId
	 * @return
	 */
	@GetMapping("/buyMovie/{userId}/{movieId}")
	public Map<String, Object> buyMovie(
			@PathVariable("userId") Integer userId, 
			@PathVariable("movieId") Integer movieId
	){
		HashMap<String, Object> result = new HashMap<>();
		// 获取用户信息，查询自己的服务即可
		User user = userService.getUserById(userId);
		
		// 查询电影信息，需要远程调用
		Movie movie = moviceServiceFeign.getMovieById(movieId);
		
		result.put("user", user);
		result.put("movie", movie);
		return result;
	}
}
