package com.itguigu.springcloud.exception;

import org.springframework.stereotype.Component;

import com.itguigu.springcloud.bean.Movie;
import com.itguigu.springcloud.service.MovieServiceFeign;

@Component
public class MovieServiceFeignException implements MovieServiceFeign {
	@Override
	public Movie getMovieById(Integer id) {
		Movie movie = new Movie(-1, "无此电影");
		return movie;
	}
}
