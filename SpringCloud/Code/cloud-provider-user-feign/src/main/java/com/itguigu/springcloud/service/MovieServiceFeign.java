package com.itguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.itguigu.springcloud.bean.Movie;
import com.itguigu.springcloud.exception.MovieServiceFeignException;

// 声明这是一个 Feign 的客户端（远程调用端）并指定远程服务地址（名称）。指定断路异常处理类
@FeignClient(value="CLOUD-PROVIDER-MOVIE", fallback = MovieServiceFeignException.class)
public interface MovieServiceFeign {
	// 要远程调用的方法。这里的方法和 Movie Controller 中的一摸一样。
	@GetMapping("/getMovieById/{id}")
	public Movie getMovieById(@PathVariable("id") Integer id);
}
